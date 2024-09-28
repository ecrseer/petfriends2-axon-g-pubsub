package gj.infnet.transportegjpetfriends.command;


import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class Comando {
    @TargetAggregateIdentifier
    private final String id;

    public Comando(String id) {
        this.id = id;
    }
}
