package gj.infnet.almoxarifadogjpetfriends.domain.external;

import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private String id;
    private Date dataPedido;
    private Long clienteId;
    private String transporteId;
    private PedidoStatus status;
    List<Produto> produtos;


    public enum PedidoStatus {
        NOVO, FECHADO, EM_PREPARACAO, EM_TRANSITO, ENTREGUE
    }


}