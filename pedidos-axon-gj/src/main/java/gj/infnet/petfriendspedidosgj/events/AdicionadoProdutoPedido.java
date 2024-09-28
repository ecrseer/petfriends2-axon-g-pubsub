package gj.infnet.petfriendspedidosgj.events;

import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.domain.Produto;
import lombok.Getter;

@Getter
public class AdicionadoProdutoPedido extends Evento {

    private final Produto produto;
    public AdicionadoProdutoPedido(String id,Produto produto) {
        super(id);
        this.produto = produto;
    }
}
