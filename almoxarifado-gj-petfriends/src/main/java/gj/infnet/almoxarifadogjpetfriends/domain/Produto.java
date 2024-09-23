package gj.infnet.almoxarifadogjpetfriends.domain;

import gj.infnet.almoxarifadogjpetfriends.command.EmPreparacao.CriarProdutoCommand;
import gj.infnet.almoxarifadogjpetfriends.events.EmPreparacao.ProdutoCriado;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

import java.io.Serializable;

@Entity
public class Produto implements Serializable {
    @EntityId
    @Id
    private String id;
    private String nome;
    private Double preco;

    @CommandHandler
    public Produto(CriarProdutoCommand comando) {
        AggregateLifecycle.apply(new ProdutoCriado(comando.id,comando.nomeProduto));
    }
    @EventSourcingHandler
    protected void on(ProdutoCriado evento) {
        this.id = evento.id;
        this.nome = evento.nomeProduto;
    }




    public Produto(String nome) {
        this.id = IdUnico.criar();
        this.nome = nome;
    }

    public Produto(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Produto(String id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto() {
        System.out.println("Prod Empt");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
