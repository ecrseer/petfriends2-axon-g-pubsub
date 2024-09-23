package gj.infnet.petfriendspedidosgj;

import gj.infnet.petfriendspedidosgj.controller.PedidoQueryController;
import gj.infnet.petfriendspedidosgj.domain.Pedido;
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

@SpringBootApplication
public class PetfriendsPedidosGjApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(PetfriendsPedidosGjApplication.class);
    @Autowired
    PedidoService pedidoService;

    @Autowired
    PedidoQueryController pedidoQueryController;

    public static void main(String[] args) {
        SpringApplication.run(PetfriendsPedidosGjApplication.class, args);
    }


    @Autowired
    private StreamBridge streamBridge;

    private void sendGclou() {
        String mensagem = "Somente uma mensagem";
        LOG.info("Enviando uma mensagem: " + mensagem);
        streamBridge.send("enviarMensagem-out-0", mensagem);
    }

    private void preparaPedido() {
//        String mensagem = "Pedido-42";
//        LOG.info("Preparando: " + mensagem);
        Pedido pedido = new Pedido(IdUnico.criar(), Date.from(Instant.now()),12L,"abds-defg42", Pedido.PedidoStatus.EM_PREPARACAO);
        MensagemGPub mensagem = new MensagemGPub("em-pppa",pedido);
        streamBridge.send("pedido-em-preparacao-topico", mensagem);
    }

    @Override
    public void run(String... args) throws Exception {
//        this.sendGclou();
        this.preparaPedido();
//        pedidoService.criarPedido(new Pedido());
        System.out.println("\n-------uepan");
    }
}
