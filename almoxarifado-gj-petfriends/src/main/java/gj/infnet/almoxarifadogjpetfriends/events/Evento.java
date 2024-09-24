package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import lombok.Getter;

@Getter
public class Evento {

    private final String id;

    public Evento(String id){
        this.id = id;
    }
}
