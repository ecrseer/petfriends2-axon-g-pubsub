package gj.infnet.almoxarifadogjpetfriends.domain;

import gj.infnet.almoxarifadogjpetfriends.command.CriarAlmoxarifadoCommand;

import gj.infnet.almoxarifadogjpetfriends.events.CriadoAlmoxarifado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;


@Aggregate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Almoxarifado implements Serializable {
    @Id
    @AggregateIdentifier
    String id;
    String nome;

    @AggregateMember


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    Produto produto;

    @CommandHandler
    public Almoxarifado(CriarAlmoxarifadoCommand comando) {
        AggregateLifecycle.apply(new CriadoAlmoxarifado(comando.getAlmoxarifadoId(), comando.getProduto()));
    }

    @EventSourcingHandler
    protected void on(CriadoAlmoxarifado evento) {
        this.id = evento.getAlmoxarifadoId();
        this.nome = "Almoxarife de Joatinga";
        this.produto = evento.getProduto();
        System.out.println(this.produto);
    }


}
