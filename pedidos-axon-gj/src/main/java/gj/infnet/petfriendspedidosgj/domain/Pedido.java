package gj.infnet.petfriendspedidosgj.domain;

import gj.infnet.petfriendspedidosgj.command.CriarPedidoCommand;
import gj.infnet.petfriendspedidosgj.command.ReceberPedidoEmTransito;
import gj.infnet.petfriendspedidosgj.events.AdicionadoProdutoPedido;
import gj.infnet.petfriendspedidosgj.events.PedidoCriado;
import gj.infnet.petfriendspedidosgj.events.PedidoEmTransito;
import gj.infnet.petfriendspedidosgj.events.PedidoFechado;
import gj.infnet.petfriendspedidosgj.infra.IdUnico;
import jakarta.persistence.*;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aggregate
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

    @Id
    @AggregateIdentifier
    private String id;
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    private Long clienteId;
    private String transporteId;
    private PedidoStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private List<Produto> produtos;


    private String endereco;

    public enum PedidoStatus {
        NOVO, FECHADO, EM_PREPARACAO, EM_TRANSITO, ENTREGUE
    }

    @CommandHandler
    public Pedido(CriarPedidoCommand criarComand) {
        String id = IdUnico.criar();
        AggregateLifecycle.apply(
                new PedidoCriado(id, criarComand.getDataPedido())
        ).andThen(() -> {
            for (Produto produto : criarComand.getProdutos()) {
                AggregateLifecycle.apply(new AdicionadoProdutoPedido(id, produto));
            }
        }).andThen(() -> {
            AggregateLifecycle.apply(
                    new PedidoFechado(id)
            );
        });

    }

    @EventSourcingHandler
    protected void on(PedidoCriado evento) {
        this.id = evento.getId();
        this.status = evento.getStatus();
        this.dataPedido = evento.getDataPedido();
        this.produtos = new ArrayList<>();
    }

    @EventSourcingHandler
    protected void on(AdicionadoProdutoPedido evento) {
        Produto produto = evento.getProduto();
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("Preço do produto não pode ser negativo");
        }
        if (produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }

        this.produtos.add(evento.getProduto());
    }

    @EventSourcingHandler
    protected void on(PedidoFechado evento) {
        this.status = evento.getStatus();
    }





    @CommandHandler
    public void on(ReceberPedidoEmTransito emTransito) {
        AggregateLifecycle.apply(new PedidoEmTransito(id));
    }
    @EventSourcingHandler
    protected void on(PedidoEmTransito evento) {
        this.status = evento.getStatus();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTransporteId() {
        return transporteId;
    }

    public void setTransporteId(String transporteId) {
        this.transporteId = transporteId;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
