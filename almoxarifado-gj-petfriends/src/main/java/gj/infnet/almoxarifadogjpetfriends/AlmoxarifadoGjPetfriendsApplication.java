package gj.infnet.almoxarifadogjpetfriends;

import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.domain.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.service.AlmoxarifadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class AlmoxarifadoGjPetfriendsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AlmoxarifadoGjPetfriendsApplication.class, args);
    }

    @Autowired
    private final AlmoxarifadoService almoxarifadoService;

    public AlmoxarifadoGjPetfriendsApplication(AlmoxarifadoService almoxarifadoService) {
        this.almoxarifadoService = almoxarifadoService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("RUNd1");
        List<Produto> produtos = List.of(new Produto("Goiaba"));
        Pedido emAndamento = new Pedido(UUID.randomUUID().toString(),
                Date.from(Instant.now()),
                12L,
                "42",
                Pedido.PedidoStatus.EM_PREPARACAO,
                produtos);
        almoxarifadoService.receberPedidoEmPreparacao(emAndamento);
    }
}
