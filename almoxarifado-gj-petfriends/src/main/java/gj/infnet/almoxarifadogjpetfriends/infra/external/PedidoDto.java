package gj.infnet.almoxarifadogjpetfriends.infra.external;


import lombok.Data;

import java.util.List;

@Data
public class PedidoDto {

        private String id;
        private int clienteId;
        private String transporteId;
        private String status;
        List produtos;

}
