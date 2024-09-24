package gj.infnet.almoxarifadogjpetfriends.command;

import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class Comando {
    @TargetAggregateIdentifier
    private final String id;

    public Comando(String id) {
        this.id = id;
    }
}
