package gj.infnet.almoxarifadogjpetfriends.domain;

import gj.infnet.almoxarifadogjpetfriends.command.CriarAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.EmPreparacao.CriarProdutoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.EnviarPedidoEmPreparacaoCommand;
import gj.infnet.almoxarifadogjpetfriends.events.CriadoAlmoxarifado;
import gj.infnet.almoxarifadogjpetfriends.events.EmPreparacao.ProdutoCriado;
import gj.infnet.almoxarifadogjpetfriends.events.EnviadoPedidoEmPreparacao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Aggregate
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Almoxarifado implements Serializable {


    @Id
    @AggregateIdentifier
    String id;
    String nome;

    @OneToMany(cascade = CascadeType.ALL)
    @AggregateMember
    List<Produto> produtos;

//    Endereco endereco;

    @CommandHandler
    public Almoxarifado(CriarAlmoxarifadoCommand comando) {
        AggregateLifecycle.apply(new CriadoAlmoxarifado(comando.id));
    }

    @EventSourcingHandler
    protected void on(CriadoAlmoxarifado evento) {
        this.id = evento.id;
        this.nome = "Almoxarife de Joatinga";
    }



    @CommandHandler
    protected void on(CriarProdutoCommand comando) {
        AggregateLifecycle.apply(new ProdutoCriado(comando.id, comando.nomeProduto));
    }
    @EventSourcingHandler
    protected void on(ProdutoCriado evento) {
        Produto produto = new Produto(evento.id, evento.nomeProduto);
        this.produtos.add(produto);
    }



    @CommandHandler
    protected void on(EnviarPedidoEmPreparacaoCommand comando) {
        AggregateLifecycle.apply(new EnviadoPedidoEmPreparacao(comando.id, comando.pedido));
    }

    @EventSourcingHandler
    protected void on(EnviadoPedidoEmPreparacao evento) {
        this.id = evento.id;
        if (this.produtos == null) {
            this.produtos = new ArrayList<>();
        }
        this.produtos.addAll(evento.produtos);
    }


}
