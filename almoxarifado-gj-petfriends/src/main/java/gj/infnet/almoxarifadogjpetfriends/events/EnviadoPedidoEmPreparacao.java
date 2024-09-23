package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.domain.external.Pedido;

import java.util.List;

public class EnviadoPedidoEmPreparacao extends Evento{
    public String pedidoId;
    public List<Produto> produtos;
    public Pedido.PedidoStatus status;
    public EnviadoPedidoEmPreparacao(String idAlmoxarifado,Pedido pedido) {
        super(idAlmoxarifado);
        this.pedidoId = pedido.getId();
        this.produtos = pedido.getProdutos();
        this.status= Pedido.PedidoStatus.EM_TRANSITO;
    }
}
