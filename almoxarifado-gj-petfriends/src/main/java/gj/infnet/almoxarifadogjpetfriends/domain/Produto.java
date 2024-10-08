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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {
    @EntityId
    @Id
    private String id;
    private String nome;
    private Double preco;

//    private Endereco endereco;

    public Produto(String nome) {
        this.nome = nome;
    }

    public Produto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
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
