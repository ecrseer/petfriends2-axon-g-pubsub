package gj.infnet.almoxarifadogjpetfriends.service;

import gj.infnet.almoxarifadogjpetfriends.command.CriarAlmoxarifadoCommand;

import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.infra.MensagemGPub;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AlmoxarifadoService {

    private final CommandGateway commandGateway;
    private final AlmoxarifadoRepository almoxarifadoRepository;

    public Consumer<Message<MensagemGPub>> pedidoEmPreparacaoTopicoSub() {
        return mensagem -> {
            Pedido pedido = (Pedido) mensagem.getPayload().getValor();
            this.receberPedidoEmPreparacao(pedido);
        };
    }

    public void receberPedidoEmPreparacao(Pedido pedido) {
        String idAlmoxarifado = "42asd";

        commandGateway.send(new CriarAlmoxarifadoCommand(
                        idAlmoxarifado, pedido.getProdutos()
                )
        );
        Optional<Almoxarifado> byId = almoxarifadoRepository.findById("42asd");
        if (byId.isEmpty()) {
            throw new IllegalStateException("N encontrei");
        } else {
            Almoxarifado almoxarifado = byId.get();
            Produto produtoEnc = almoxarifado.getProdutos().get(0);
            System.out.println(produtoEnc.getNome());
            System.out.println(produtoEnc.getId());

        }


    }


}
