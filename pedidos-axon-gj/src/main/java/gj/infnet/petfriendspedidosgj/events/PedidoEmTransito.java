package gj.infnet.petfriendspedidosgj.events;

import gj.infnet.petfriendspedidosgj.domain.Pedido;
import lombok.Getter;

@Getter
public class PedidoEmTransito extends Evento {

    private final Pedido.PedidoStatus status;

    public PedidoEmTransito(String id) {
        super(id);
        this.status = Pedido.PedidoStatus.EM_TRANSITO;
    }
}
