package gj.infnet.almoxarifadogjpetfriends.infra;

import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
public class MensagemGPub {
    private String titulo;
    private Object valor;

    public MensagemGPub(String titulo, Object valor) {
        this.titulo = titulo;
        this.valor = valor;
    }

}
