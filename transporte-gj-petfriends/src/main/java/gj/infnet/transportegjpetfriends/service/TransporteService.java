package gj.infnet.transportegjpetfriends.service;


import gj.infnet.transportegjpetfriends.command.EnviarPedidoEmTransitoCommand;
import gj.infnet.transportegjpetfriends.infra.MensagemGPub;
import gj.infnet.transportegjpetfriends.infra.external.PedidoAlmoxarifado;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.Message;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class TransporteService {

    private final CommandGateway commandGateway;
    private final TransporteRepository transporteRepository;
    private final StreamBridge streamBridge;


    @Bean
    public Consumer<Message<MensagemGPub>> pedidoEmTransitoTopicoSub() {
        try {
            return this::recebeEmTransito;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void recebeEmTransito(Message<MensagemGPub> mensagem) {
        Object valor = mensagem.getPayload().getValor();
        var json = (LinkedHashMap<String, Object>) valor;
        PedidoAlmoxarifado pedido = new PedidoAlmoxarifado(json);

        commandGateway.send(new EnviarPedidoEmTransitoCommand(pedido.getId(),
                pedido.getProdutosId(), pedido.getClienteEndereco())
        );


        this.streamBridge.send("pedido-enviado-em-transito-topico", mensagem.getPayload());
    }


}
