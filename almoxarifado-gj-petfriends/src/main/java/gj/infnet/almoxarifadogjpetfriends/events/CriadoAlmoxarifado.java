package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.*;

import java.util.List;


@Getter
public class CriadoAlmoxarifado extends Evento {
    private final Produto produto;

    public CriadoAlmoxarifado(String id, Produto produto) {
        super(id);
        this.produto = produto;
    }
}
