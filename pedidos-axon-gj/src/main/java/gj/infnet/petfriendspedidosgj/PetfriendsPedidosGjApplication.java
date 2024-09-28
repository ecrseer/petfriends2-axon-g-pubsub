package gj.infnet.petfriendspedidosgj;

import gj.infnet.petfriendspedidosgj.controller.PedidoQueryController;
import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.domain.Produto;
import gj.infnet.petfriendspedidosgj.infra.IdUnico;
import gj.infnet.petfriendspedidosgj.infra.MensagemGPub;
import gj.infnet.petfriendspedidosgj.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@SpringBootApplication
public class PetfriendsPedidosGjApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(PetfriendsPedidosGjApplication.class);
    @Autowired
    PedidoService pedidoService;


    public static void main(String[] args) {
        SpringApplication.run(PetfriendsPedidosGjApplication.class, args);
    }



    private void preparaPedido() {
        Pedido mockPedido = new Pedido(IdUnico.criar(),
                Date.from(Instant.now()), 12L
                , null,
                Pedido.PedidoStatus.EM_PREPARACAO,
                List.of(
                        new Produto("32", "Brinquedo para Pássaros", 100D)
                )
        );
        pedidoService.criarPedido(mockPedido);
    }

    @Override
    public void run(String... args) throws Exception {
        this.preparaPedido();
    }
}
