package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import lombok.Getter;


@Getter
public class PedidoDespachado extends Evento {
    private final Pedido.PedidoStatus status;

    public PedidoDespachado(String id) {
        super(id);
        this.status = Pedido.PedidoStatus.EM_TRANSITO;
    }
}
