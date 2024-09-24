package gj.infnet.almoxarifadogjpetfriends.domain;


import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

import java.io.Serializable;

@Entity
@EqualsAndHashCode
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {
    @EntityId
    @Id
    private String id;
    private String nome;
    private Double preco;

    public Produto(String nome) {
        this.nome = nome;
    }
}
