package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.domain.external.Pedido;

public class EnviadoPedidoEmPreparacao extends Evento{
    public String pedidoId;
    public Pedido.PedidoStatus status;
    public EnviadoPedidoEmPreparacao(String pedidoId) {
        super();
        this.pedidoId = pedidoId;
        this.status= Pedido.PedidoStatus.EM_TRANSITO;
    }
}
