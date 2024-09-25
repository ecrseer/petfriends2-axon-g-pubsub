package gj.infnet.almoxarifadogjpetfriends.command;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Getter;

import java.util.List;


@Getter
public class AlterarAlmoxarifadoCommand extends Comando {
    private final String nome;

    public AlterarAlmoxarifadoCommand(String id, String nome) {
        super(id);
        this.nome = nome;
    }
}
