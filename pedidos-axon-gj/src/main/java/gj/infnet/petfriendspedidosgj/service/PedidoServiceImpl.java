package gj.infnet.petfriendspedidosgj.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gj.infnet.petfriendspedidosgj.command.CriarPedidoCommand;
import gj.infnet.petfriendspedidosgj.command.ReceberPedidoEmTransito;
import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.events.PedidoEmTransito;
import gj.infnet.petfriendspedidosgj.events.PedidoFechado;
import gj.infnet.petfriendspedidosgj.infra.MensagemGPub;
import gj.infnet.petfriendspedidosgj.infra.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
@Slf4j
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
    public void on(PedidoFechado event) {
        Optional<Pedido> optPedido = pedidoRepository.findById(event.getId());
        if (optPedido.isPresent()) {
            MensagemGPub msg = new MensagemGPub("Almoxarifado:preparar pedido", "optPedido.get()");
            streamBridge.send("pedido-em-preparacao-topico", msg);

        }
    }


    @Bean
    public Consumer<Message<String>> pedidoEmPreparacaoTopicoSub() {
        try {
            return this::recebePedidoPreparadoAlmoxarifado;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void recebePedidoPreparadoAlmoxarifado(Message<String> mensagem) {
        try {
            log.info(mensagem.getPayload());
//            Object valor = mensagem.getPayload().getValor();
//            var json = (LinkedHashMap<String, Object>) valor;
//            ReceberPedidoEmTransito comando = new ObjectMapper().convertValue(json, ReceberPedidoEmTransito.class);
//            if (comando.getStatus() == Pedido.PedidoStatus.EM_TRANSITO) {
//                commandGateway.send(comando);
//            }
        } catch (Exception e) {
            log.info("Erro ao receber pedido preparado no almoxarifado", e.getMessage());
            throw e;
        }
    }

    @EventHandler
    public void on(PedidoEmTransito event) {
        Optional<Pedido> optPedido = pedidoRepository.findById(event.getId());
        log.info("Pedido em trânsito: " + event);
        streamBridge.send("pedido-em-transito-topico", optPedido.get());
    }
}
