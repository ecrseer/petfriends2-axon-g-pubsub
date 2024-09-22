package gj.infnet.almoxarifadogjpetfriends.domain;

import lombok.Data;

@Data
public class Endereco {
    String CEP;

    String logradouro;
    Long numero;

    String bairro;
    String cidade;
    String estado;

    public Endereco(String CEP, String logradouro, Long numero) {
        this.CEP = CEP;
        this.logradouro = logradouro;
        this.numero = numero;
    }
}
