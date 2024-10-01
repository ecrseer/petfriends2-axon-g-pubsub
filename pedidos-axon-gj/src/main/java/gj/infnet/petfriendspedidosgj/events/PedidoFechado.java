package gj.infnet.petfriendspedidosgj.events;

import gj.infnet.petfriendspedidosgj.domain.Pedido;
import lombok.Getter;

import java.util.Date;


@Getter
public class PedidoFechado extends Evento {

    private final Pedido.PedidoStatus status;

    public PedidoFechado(String id) {
        super(id);
        this.status = Pedido.PedidoStatus.FECHADO;
    }
}
