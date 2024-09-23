package gj.infnet.almoxarifadogjpetfriends.service;

import gj.infnet.almoxarifadogjpetfriends.command.CriarAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.EmPreparacao.CriarProdutoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.EnviarPedidoEmPreparacaoCommand;
import gj.infnet.almoxarifadogjpetfriends.domain.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import gj.infnet.almoxarifadogjpetfriends.infra.MensagemGPub;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AlmoxarifadoService {

    private final CommandGateway commandGateway;

    public Consumer<Message<MensagemGPub>> pedidoEmPreparacaoTopicoSub() {
        return mensagem -> {
            Pedido pedido = (Pedido) mensagem.getPayload().getValor();
            this.receberPedidoEmPreparacao(pedido);
        };
    }

    public void receberPedidoEmPreparacao(Pedido pedido) {
        String idAlmoxarifado = "42asd";
        commandGateway.send(new CriarAlmoxarifadoCommand(idAlmoxarifado));
        commandGateway.send
                (new CriarProdutoCommand("42asd","Bananada de Joatinga")
                );
        commandGateway.send(new EnviarPedidoEmPreparacaoCommand(idAlmoxarifado, pedido, pedido.getProdutos()));

    }


}
