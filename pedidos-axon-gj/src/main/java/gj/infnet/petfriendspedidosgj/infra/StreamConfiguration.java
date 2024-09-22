package gj.infnet.petfriendspedidosgj.infra;

import gj.infnet.petfriendspedidosgj.command.CriarPedidoCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class StreamConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(StreamConfiguration.class);

    private final CommandGateway commandGateway;

    public StreamConfiguration(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

//    @Bean
//    public Consumer<Message<String>> receberMensagem() {
//        return mensagem -> {
//            LOG.info("receberMensagem: " + mensagem.getPayload());
////            commandGateway.send(new CriarPedidoCommand());
//        };
//    }

}
