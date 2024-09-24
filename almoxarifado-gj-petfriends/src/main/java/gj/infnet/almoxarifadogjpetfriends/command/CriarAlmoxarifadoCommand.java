package gj.infnet.almoxarifadogjpetfriends.command;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
public class CriarAlmoxarifadoCommand extends Comando {
    private final List<Produto> produtos;

    public CriarAlmoxarifadoCommand(String id, List<Produto> produtos) {
        super(id);
        this.produtos = produtos;
    }
}
