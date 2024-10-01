package gj.infnet.almoxarifadogjpetfriends.service;

import gj.infnet.almoxarifadogjpetfriends.command.AdicionarProdutosAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.AlterarAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.CriarAlmoxarifadoCommand;

import gj.infnet.almoxarifadogjpetfriends.command.EnviarPedidoEmPreparacaoCommand;
import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import gj.infnet.almoxarifadogjpetfriends.infra.external.MocksData;
import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.infra.MensagemGPub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlmoxarifadoService {

    private final CommandGateway commandGateway;
    private final AlmoxarifadoRepository almoxarifadoRepository;
    private final StreamBridge streamBridge;

    @Bean
    public Consumer<Message<MensagemGPub>> pedidoEmPreparacaoTopicoSub() {

        return this::recebePedidoEmPreparacao;

    }

    @SuppressWarnings("unchecked")
    private void recebePedidoEmPreparacao(Message<MensagemGPub> mensagem) {
        try {

            Object valor = mensagem.getPayload().getValor();
            var json = (LinkedHashMap<String, Object>) valor;
            Pedido pedido = new Pedido(json);
            if (pedido.getStatus() == Pedido.PedidoStatus.FECHADO) {
                Almoxarifado almoxarifado = almoxarifadoRepository.findAll().get(0);
                this.removeProdutos(almoxarifado.getId(), pedido);
                pedido.setStatus(Pedido.PedidoStatus.EM_TRANSITO);
                MensagemGPub mensagemComPedido = new MensagemGPub("Transporte: notificar pedido em transito", pedido);
                streamBridge.send("pedido-preparado-topico", mensagemComPedido);
            }

        } catch (Exception e) {
            log.info("Erro ao receber pedido em preparação", e);
        }

    }


    public Almoxarifado criaAlmoxarifado(Pedido pedido) {
        String idAlmoxarifado = IdUnico.criar();
        commandGateway.send(new CriarAlmoxarifadoCommand(idAlmoxarifado, pedido.getProdutos()));
        this.adicionaProdutos(idAlmoxarifado, MocksData.petProdutos);
        return this.obterAlmoxarife(idAlmoxarifado);
    }

    public Almoxarifado criaAlmoxarifado() {
        String idAlmoxarifado = IdUnico.criar();
        commandGateway.send(new CriarAlmoxarifadoCommand(idAlmoxarifado, List.of()));
        this.adicionaProdutos(idAlmoxarifado, MocksData.petProdutos);
        return this.obterAlmoxarife(idAlmoxarifado);
    }

    public Almoxarifado obterAlmoxarife(String idAlmoxarifado) {
        Optional<Almoxarifado> byId = almoxarifadoRepository.findById(idAlmoxarifado);
        if (byId.isEmpty()) {
            throw new IllegalStateException("N encontrei");
        } else {
            Almoxarifado almoxarifado = byId.get();
            return almoxarifado;
        }
    }

    public void adicionaProdutos(String idAlmoxarifado, List<Produto> produto) {
        commandGateway.send(new AdicionarProdutosAlmoxarifadoCommand(idAlmoxarifado, produto));
        Almoxarifado almoxarifado = obterAlmoxarife(idAlmoxarifado);
        if (almoxarifado.getProdutos().size() < 2) {
            throw new IllegalStateException("n adicionou");
        }

    }

    public void removeProdutos(String idAlmoxarifado, Pedido pedido) {
        commandGateway.send(new EnviarPedidoEmPreparacaoCommand(idAlmoxarifado, pedido));
        Almoxarifado almoxarifado1 = obterAlmoxarife(idAlmoxarifado);
        if (almoxarifado1 != null) {
            log.info("Produtos removidos", almoxarifado1);
        }
    }

    public void mockAdicionaNome(String almoxarifado) {
        this.commandGateway.send(new AlterarAlmoxarifadoCommand(almoxarifado, "Uepa"));
        Almoxarifado almoxarifado1 = obterAlmoxarife(almoxarifado);
        if (almoxarifado1 != null) {
            log.info("editado", almoxarifado1);
        }
    }


}
