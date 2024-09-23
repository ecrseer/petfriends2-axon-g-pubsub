package gj.infnet.almoxarifadogjpetfriends.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {


    @Id
    private String id;

    private String nome;
    private String descricao;
    private Double preco;
}
