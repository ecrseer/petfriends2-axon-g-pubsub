package gj.infnet.transportegjpetfriends.domain;

import lombok.Data;

@Data
public class TransporteEndereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
