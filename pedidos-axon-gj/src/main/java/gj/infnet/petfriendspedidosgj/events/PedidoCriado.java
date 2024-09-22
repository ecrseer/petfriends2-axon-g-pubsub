package gj.infnet.petfriendspedidosgj.events;

import gj.infnet.petfriendspedidosgj.domain.Pedido;

public class PedidoCriado extends Evento {

    public Pedido.PedidoStatus status;
    public PedidoCriado() {
        super();
        this.status= Pedido.PedidoStatus.NOVO;
    }
}
