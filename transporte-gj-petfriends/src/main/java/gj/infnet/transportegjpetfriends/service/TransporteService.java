package gj.infnet.transportegjpetfriends.service;


import gj.infnet.transportegjpetfriends.command.EnviarPedidoEmTransitoCommand;
import gj.infnet.transportegjpetfriends.infra.IdUnico;
import gj.infnet.transportegjpetfriends.infra.MensagemGPub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransporteService {

    private final CommandGateway commandGateway;

    @Bean
    public Consumer<Message<MensagemGPub>> pedidoEmTransitoTopicoSub() {
        try {
            System.out.println();
            return this::recebePedidoEmTransito;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void recebePedidoEmTransito(Message<MensagemGPub> mensagemGPubMessage) {
        log.info("Criando transporte", mensagemGPubMessage.toString());
        commandGateway.send(new EnviarPedidoEmTransitoCommand(IdUnico.criar(),
                List.of("1", "2"), "Rua dos Bobos, 0"));
    }


}
