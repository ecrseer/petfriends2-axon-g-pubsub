package gj.infnet.almoxarifadogjpetfriends.infra.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedHashMap;
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
    private String endereco;


    public enum PedidoStatus {
        NOVO, FECHADO, EM_PREPARACAO, EM_TRANSITO, ENTREGUE
    }

    public Pedido(LinkedHashMap<String, Object> json) {
        Pedido pedido = new ObjectMapper().convertValue(json, Pedido.class);

        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.clienteId = pedido.getClienteId();
        this.transporteId = pedido.getTransporteId();
        this.status = pedido.getStatus();
        this.produtos = pedido.getProdutos();
    }


}
