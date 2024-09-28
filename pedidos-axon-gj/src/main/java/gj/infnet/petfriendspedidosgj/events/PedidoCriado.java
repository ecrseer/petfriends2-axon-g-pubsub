package gj.infnet.petfriendspedidosgj.events;

import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.infra.IdUnico;
import lombok.Getter;

import java.util.Date;


@Getter
public class PedidoCriado extends Evento {


    private final Pedido.PedidoStatus status;
    private final Date dataPedido;

    public PedidoCriado(String id,Date dataPedido) {
        super(id);
        this.status = Pedido.PedidoStatus.NOVO;
        this.dataPedido = dataPedido;
    }
}
