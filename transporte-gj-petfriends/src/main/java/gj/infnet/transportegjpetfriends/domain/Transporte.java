package gj.infnet.transportegjpetfriends.domain;

import jakarta.persistence.Id;
import org.axonframework.modelling.command.AggregateIdentifier;

public class Transporte {
    @Id
    @AggregateIdentifier
    private String id;
    private String status;
}
