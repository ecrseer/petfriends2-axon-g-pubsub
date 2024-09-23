package gj.infnet.almoxarifadogjpetfriends.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco implements Serializable {
    @Id
    private String id;

    private String CEP;

    private String logradouro;
    private Long numero;

    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String CEP, String logradouro, Long numero) {
        this.CEP = CEP;
        this.logradouro = logradouro;
        this.numero = numero;
    }
}
