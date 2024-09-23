package gj.infnet.almoxarifadogjpetfriends.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.List;




@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Almoxarifado implements Serializable {

    @Id
    String id;
//    Endereco endereco;
//    List<Produto> produtos;
}
