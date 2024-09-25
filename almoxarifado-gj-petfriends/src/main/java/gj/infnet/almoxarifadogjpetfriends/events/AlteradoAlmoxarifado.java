package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Getter;

@Getter
public class AlteradoAlmoxarifado extends Evento {
    private final String nome;

    public AlteradoAlmoxarifado(String id, String nome) {
        super(id);
        this.nome = nome;
    }
}
