package gj.infnet.petfriendspedidosgj.command;

import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.domain.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReceberPedidoEmTransito extends Comando {


    private String id;
    private Pedido.PedidoStatus status;

    public ReceberPedidoEmTransito(String id,
                                   Pedido.PedidoStatus status) {
        super(id);
        this.status = status;
    }

    public ReceberPedidoEmTransito() {

    }

}
