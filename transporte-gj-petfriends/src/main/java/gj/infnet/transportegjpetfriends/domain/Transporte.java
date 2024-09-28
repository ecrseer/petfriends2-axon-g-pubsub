package gj.infnet.transportegjpetfriends.domain;

import gj.infnet.transportegjpetfriends.command.EnviarPedidoEmTransitoCommand;
import gj.infnet.transportegjpetfriends.events.AdicionadoProdutoTransporte;
import gj.infnet.transportegjpetfriends.events.IniciadoTransporte;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;


@Aggregate
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transporte {
    @Id
    @AggregateIdentifier
    private String id;
    private String status;
    @ElementCollection
    private List<String> produtosId;
    private String enderecoEntrega;



    @CommandHandler
    public Transporte(EnviarPedidoEmTransitoCommand comando) {
        AggregateLifecycle.apply(new IniciadoTransporte(
                comando.getId(), comando.getEndereco()
        ));

        for (String produtoId : comando.getProdutosId()) {
            AggregateLifecycle.apply(new AdicionadoProdutoTransporte(
                    comando.getId(), produtoId
            ));
        }

    }
    @EventSourcingHandler
    protected void on(IniciadoTransporte evento) {
        this.id = evento.getId();
        this.enderecoEntrega = evento.getEndereco();

    }

    @EventSourcingHandler
    protected void on(AdicionadoProdutoTransporte evento) {
        this.id = evento.getId();
        this.produtosId.add(evento.getProdutoId());
    }

}
