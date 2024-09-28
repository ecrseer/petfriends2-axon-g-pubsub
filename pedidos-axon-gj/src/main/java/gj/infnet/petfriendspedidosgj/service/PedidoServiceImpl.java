package gj.infnet.petfriendspedidosgj.service;

import gj.infnet.petfriendspedidosgj.command.CriarPedidoCommand;
import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.events.PedidoCriado;
import gj.infnet.petfriendspedidosgj.infra.IdUnico;
import gj.infnet.petfriendspedidosgj.infra.MensagemGPub;
import gj.infnet.petfriendspedidosgj.infra.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final CommandGateway commandGateway;
    private final StreamBridge streamBridge;
    private final PedidoRepository pedidoRepository;


    @Override
    public String criarPedido(Pedido pedido) {

        commandGateway.send(
                new CriarPedidoCommand(pedido.getProdutos(), new Date())
        );
        return "criado";
    }

    @EventHandler
    public void on(PedidoCriado event) {
        Optional<Pedido> optPedido = pedidoRepository.findById(event.getId());
        if (optPedido.isPresent()) {
            MensagemGPub mensagem = new MensagemGPub("Almoxarifado:preparar pedido", optPedido.get());
            streamBridge.send("pedido-em-preparacao-topico", mensagem);

        }
    }
}
