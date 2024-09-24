package gj.infnet.almoxarifadogjpetfriends.command;

import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;

import java.util.List;

public class EnviarPedidoEmPreparacaoCommand extends Comando {
    public Pedido pedido;
    public Produto produto;

    public EnviarPedidoEmPreparacaoCommand(String idAlmoxarifado,Pedido pedido,Produto produto) {
        super(idAlmoxarifado);
        this.pedido = pedido;
        this.produto = produto;
    }
}
