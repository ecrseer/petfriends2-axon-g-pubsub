package gj.infnet.petfriendspedidosgj.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto implements Serializable {

    @Id
    private String id;
    private String nome;
    private Double preco;

    public Produto(String nome) {
        this.nome = nome;
    }
    public Produto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }

}
