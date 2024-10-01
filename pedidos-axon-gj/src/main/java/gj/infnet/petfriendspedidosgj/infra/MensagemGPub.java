package gj.infnet.petfriendspedidosgj.infra;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
