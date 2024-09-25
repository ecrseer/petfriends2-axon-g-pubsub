package gj.infnet.almoxarifadogjpetfriends.command;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Getter;

import java.util.List;


@Getter
public class AdicionarProdutosAlmoxarifadoCommand extends Comando {
    private final List<Produto> produtos;

    public AdicionarProdutosAlmoxarifadoCommand(String id, List<Produto> produtos) {
        super(id);
        this.produtos = produtos;
    }
}
