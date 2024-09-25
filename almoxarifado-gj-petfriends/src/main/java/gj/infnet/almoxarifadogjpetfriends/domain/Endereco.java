package gj.infnet.almoxarifadogjpetfriends.domain;


import lombok.Data;

@Data
public class Endereco {
    private String id;
    
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

}
