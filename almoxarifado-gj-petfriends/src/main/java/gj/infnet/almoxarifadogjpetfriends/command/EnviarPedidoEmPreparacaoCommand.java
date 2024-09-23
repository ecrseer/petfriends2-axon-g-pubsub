package gj.infnet.almoxarifadogjpetfriends.command;

import gj.infnet.almoxarifadogjpetfriends.domain.external.Pedido;

public class EnviarPedidoEmPreparacaoCommand extends Comando {
    public String pedidoId;
    public Pedido.PedidoStatus status;

    public EnviarPedidoEmPreparacaoCommand(Pedido pedido) {
        super();
        this.pedidoId = pedido.getId();
        this.status= Pedido.PedidoStatus.EM_TRANSITO;
    }
}
