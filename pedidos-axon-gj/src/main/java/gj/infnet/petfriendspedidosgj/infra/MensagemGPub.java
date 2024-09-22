package gj.infnet.petfriendspedidosgj.infra;

import lombok.Data;

@Data
public class MensagemGPub {
    private String titulo;
    private Object valor;

    public MensagemGPub(String titulo, Object valor) {
        this.titulo = titulo;
        this.valor = valor;
    }
}
