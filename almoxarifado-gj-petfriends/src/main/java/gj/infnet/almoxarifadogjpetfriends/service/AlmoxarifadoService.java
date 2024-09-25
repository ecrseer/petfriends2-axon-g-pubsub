package gj.infnet.almoxarifadogjpetfriends.service;

import gj.infnet.almoxarifadogjpetfriends.command.AdicionarProdutosAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.AlterarAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.CriarAlmoxarifadoCommand;

import gj.infnet.almoxarifadogjpetfriends.command.EnviarPedidoEmPreparacaoCommand;
import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.infra.MensagemGPub;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AlmoxarifadoService {

    private final CommandGateway commandGateway;
    private final AlmoxarifadoRepository almoxarifadoRepository;


    @Bean
    public Consumer<Message<MensagemGPub>> pedidoEmPreparacaoTopicoSub() {
        try {
            return this::recebePedidoEmPreparacao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void recebePedidoEmPreparacao(Message<MensagemGPub> mensagem) {
        Object valor = mensagem.getPayload().getValor();
        var json = (LinkedHashMap<String, Object>) valor;
        Pedido pedido = new Pedido(json);

        Almoxarifado almoxarifado = almoxarifadoRepository.findAll().get(0);
        this.removeProdutos(almoxarifado.getId(), pedido);
    }


    public Almoxarifado criaAlmoxarifado(Pedido pedido) {
        String idAlmoxarifado = IdUnico.criar();
        commandGateway.send(
                new CriarAlmoxarifadoCommand(idAlmoxarifado,
                        pedido.getProdutos())
        );
        this.adicionaProdutos(idAlmoxarifado, mockProdutos);
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
            System.out.println(almoxarifado1);
        }
    }

    public void mockAdicionaNome(String almoxarifado) {
        this.commandGateway.send(new AlterarAlmoxarifadoCommand(almoxarifado, "Uepa"));
        Almoxarifado almoxarifado1 = obterAlmoxarife(almoxarifado);
        if (almoxarifado1 != null) {
            System.out.println(almoxarifado1);
        }
    }

    private List<Produto> mockProdutos = List.of(
            new Produto(IdUnico.criar(), "Bolinha de Morder", 42D),
            new Produto("26", "Ração para Cães", 2300D),
            new Produto("27", "Ração para Gatos", 14D),
            new Produto("28", "Coleira", 44D),
            new Produto("29", "Petisco para Cães", 12D),
            new Produto("30", "Caminha para Cães", 100D),
            new Produto("31", "Caminha para Gatos", 100D),
            new Produto("32", "Brinquedo para Pássaros", 100D),
            new Produto("33", "Comedouro para Cães", 100D),
            new Produto("34", "Bebedouro Automático", 100D)
    );

}
