package gj.infnet.transportegjpetfriends.command;


import lombok.Getter;

import java.util.List;

@Getter
public class EnviarPedidoEmTransitoCommand extends Comando {
    private final List<String> produtosId;
    private final String endereco;

    public EnviarPedidoEmTransitoCommand(String id, List<String> produtosId, String endereco) {
        super(id);
        this.produtosId = produtosId;
        this.endereco = endereco;
    }
}
