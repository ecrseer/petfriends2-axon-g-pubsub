package gj.infnet.transportegjpetfriends.service;


import gj.infnet.transportegjpetfriends.command.EnviarPedidoEmTransitoCommand;
import gj.infnet.transportegjpetfriends.infra.IdUnico;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransporteService {

    private final CommandGateway commandGateway;

    public String criarTransporte() {
        log.info("Criando transporte");
        commandGateway.send(new EnviarPedidoEmTransitoCommand(IdUnico.criar(), List.of("1", "2"), "Rua dos Bobos, 0"));
        return "criado";
    }
}
