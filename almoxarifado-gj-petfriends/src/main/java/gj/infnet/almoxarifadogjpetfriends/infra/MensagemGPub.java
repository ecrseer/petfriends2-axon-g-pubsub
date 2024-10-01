package gj.infnet.almoxarifadogjpetfriends.infra;

import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensagemGPub {
    private String titulo;
    private Object valor;


}
