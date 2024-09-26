package gj.infnet.transportegjpetfriends.command;


import lombok.Getter;

import java.util.List;


@Getter
public class EnviarPedidoEmTransitoCommand extends Comando {
    private final List<String> produtos;
    private final String endereco;

    public EnviarPedidoEmTransitoCommand(String id, List<String> produtos,
                                         String endereco) {
        super(id);
        this.produtos = produtos;
        this.endereco = endereco;
    }
}
