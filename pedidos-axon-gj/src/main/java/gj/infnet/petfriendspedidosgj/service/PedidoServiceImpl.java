package gj.infnet.petfriendspedidosgj.service;

import gj.infnet.petfriendspedidosgj.command.CriarPedidoCommand;
import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.events.PedidoCriado;
import gj.infnet.petfriendspedidosgj.infra.IdUnico;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PedidoServiceImpl implements PedidoService {

    private final CommandGateway commandGateway;

    public PedidoServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public String criarPedido(Pedido pedido) {

        commandGateway.send(
                new CriarPedidoCommand(pedido.getProdutos(),new Date())
        );
        return "criado";
    }

    @EventHandler
    public void on(PedidoCriado event) {
        // Build the message payload
//        MensagemPedidoPreparacao mensagem = new MensagemPedidoPreparacao(event.getId(), event.getDetails());
//        // Send message via streamBridge
//        streamBridge.send("pedido-em-preparacao-topico", mensagem);
    }
}
