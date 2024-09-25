package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.*;

@Getter
public class CriadoAlmoxarifado extends Evento {

    public CriadoAlmoxarifado(String id) {
        super(id);
    }
}
