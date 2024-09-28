package gj.infnet.transportegjpetfriends.command;


import lombok.Getter;

import java.util.List;

@Getter
public class PedidoEnviadoEmTransitoCommand extends Comando {
    private final List<String> produtosId;

    public PedidoEnviadoEmTransitoCommand(String id) {
        super(id);
    }
}
