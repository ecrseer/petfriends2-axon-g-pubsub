package gj.infnet.transportegjpetfriends.events;


import lombok.Getter;

import java.util.List;

@Getter
public class PedidoEnviadoEmTransito extends Evento {

    private final List<String> produtosId;
    private final String endereco;

    public PedidoEnviadoEmTransito(String id, List<String> produtosId,
                                   String endereco
    ) {
        super(id);
        this.endereco = endereco;
        this.produtosId = produtosId;
    }
}
