package gj.infnet.petfriendspedidosgj.command;

import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.domain.Produto;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class CriarPedidoCommand extends Comando {

    private final List<Produto> produtos;
    private final Date dataPedido;
    public CriarPedidoCommand(List<Produto> produtos, Date dataPedido) {
        super();
        this.produtos = produtos;
        this.dataPedido = dataPedido;
    }
}
