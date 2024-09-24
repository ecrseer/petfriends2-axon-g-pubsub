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
import java.util.ArrayList;
import java.util.List;


@Aggregate
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Almoxarifado implements Serializable {
    @Id
    @AggregateIdentifier
    private String id;
    private String nome;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private List<Produto> produtos = new ArrayList<>();

    @CommandHandler
    public Almoxarifado(CriarAlmoxarifadoCommand comando) {
        List<Produto> comandoProdutos = comando.getProdutos();

        AggregateLifecycle.apply(new CriadoAlmoxarifado(comando.getId(), comandoProdutos.get(0)));
    }

    @EventSourcingHandler
    protected void on(CriadoAlmoxarifado evento) {
        this.id = evento.getId();
        this.nome = "Almoxarife de Joatinga";
        this.produtos.add(evento.getProduto());
        System.out.println(this.produtos);
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
