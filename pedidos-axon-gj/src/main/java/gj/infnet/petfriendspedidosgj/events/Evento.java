package gj.infnet.petfriendspedidosgj.events;

import gj.infnet.petfriendspedidosgj.infra.IdUnico;

public class Evento {

    public String id;

    public Evento(){
        String id = IdUnico.criar();
        this.id=id;
    }
    public Evento(String id){
        this.id = id;
    }
}
