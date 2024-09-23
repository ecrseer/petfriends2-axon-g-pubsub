package gj.infnet.almoxarifadogjpetfriends.domain.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private String id;
    private Date dataPedido;
    private Long clienteId;
    private String transporteId;
    private PedidoStatus status;


    public enum PedidoStatus {
        NOVO, FECHADO, EM_PREPARACAO, EM_TRANSITO, ENTREGUE
    }


}