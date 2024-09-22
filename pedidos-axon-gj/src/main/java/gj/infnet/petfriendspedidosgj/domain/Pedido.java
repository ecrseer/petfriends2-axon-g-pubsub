package gj.infnet.petfriendspedidosgj.domain;

import gj.infnet.petfriendspedidosgj.command.CriarPedidoCommand;
import gj.infnet.petfriendspedidosgj.events.PedidoCriado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.Date;

@Aggregate
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido  {

    @Id
    @AggregateIdentifier
    private String id;
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    private Long clienteId;
    private String transporteId;
    private PedidoStatus status;



    public enum PedidoStatus {
        NOVO, FECHADO, CANCELADO, ENVIADO
    }

    @CommandHandler
    public Pedido(CriarPedidoCommand criarComand){

        AggregateLifecycle.apply(new PedidoCriado());
    }

    @EventSourcingHandler
    protected void on(PedidoCriado evento){
        this.id=evento.id;
        this.status=evento.status;
    }

}