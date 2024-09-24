package gj.infnet.almoxarifadogjpetfriends.command;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
public class CriarAlmoxarifadoCommand extends Comando {
    private final Produto produto;

    public CriarAlmoxarifadoCommand(String id, Produto produto) {
        super(id);
        this.produto = produto;
    }
}
