package gj.infnet.almoxarifadogjpetfriends.command;

import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class Comando {

    @TargetAggregateIdentifier
    public String id;

    public Comando(){
        this.id= IdUnico.criar();
    }
    public Comando(String id) {
        this.id = id;
    }

}
