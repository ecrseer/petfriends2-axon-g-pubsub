package gj.infnet.petfriendspedidosgj.service;

import gj.infnet.petfriendspedidosgj.command.CriarPedidoCommand;
import gj.infnet.petfriendspedidosgj.domain.Pedido;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;


@Service
public class PedidoServiceImpl implements PedidoService {

    private final CommandGateway commandGateway;

    public PedidoServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public String criarPedido(Pedido pedido) {
        commandGateway.send(new CriarPedidoCommand());
        return "criado";
    }
}
