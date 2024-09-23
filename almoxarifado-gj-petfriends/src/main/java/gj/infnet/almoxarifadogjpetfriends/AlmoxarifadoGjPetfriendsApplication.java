package gj.infnet.almoxarifadogjpetfriends;

import gj.infnet.almoxarifadogjpetfriends.domain.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class AlmoxarifadoGjPetfriendsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AlmoxarifadoGjPetfriendsApplication.class, args);
    }

    @Autowired
    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    public AlmoxarifadoGjPetfriendsApplication(MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("RUNd1");
        Pedido emAndamento = new Pedido(UUID.randomUUID().toString(),
                Date.from(Instant.now()),
                12L,
                "42",
                Pedido.PedidoStatus.EM_PREPARACAO);
        movimentacaoEstoqueService.receberPedidoEmPreparacao(emAndamento);
    }
}
