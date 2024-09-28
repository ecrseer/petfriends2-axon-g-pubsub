package gj.infnet.petfriendspedidosgj.command;

import gj.infnet.petfriendspedidosgj.infra.IdUnico;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class Comando {

    @TargetAggregateIdentifier
    private String id;



    public String getId() {
        return id;
    }

    public Comando(String id) {
        this.id = id;
    }

    public Comando() {

    }

}
