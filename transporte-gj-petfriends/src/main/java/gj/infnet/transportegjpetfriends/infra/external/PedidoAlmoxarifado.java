package gj.infnet.transportegjpetfriends.infra.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoAlmoxarifado {

    private String id;
    private Long clienteId;
    private String clienteEndereco;
    List<String> produtosId;



    public PedidoAlmoxarifado(LinkedHashMap<String, Object> json) {
        PedidoAlmoxarifado pedidoAlmoxarifado = new ObjectMapper().convertValue(json, PedidoAlmoxarifado.class);

        this.id = pedidoAlmoxarifado.getId();
        this.clienteId = pedidoAlmoxarifado.getClienteId();
        this.produtos = pedidoAlmoxarifado.getProdutos();
    }


}
