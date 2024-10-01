package gj.infnet.almoxarifadogjpetfriends;

import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import gj.infnet.almoxarifadogjpetfriends.infra.external.MocksData;
import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.service.AlmoxarifadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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


//    public Pedido mockPedidoFactory(List<Produto> prods) {
//        Pedido emAndamento = new Pedido(UUID.randomUUID().toString(),
//                Date.from(Instant.now()),
//                12L,
//                "42",
//                Pedido.PedidoStatus.EM_PREPARACAO,
//                prods);
//        return emAndamento;
//    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("inicializa almoxarifado");
        Almoxarifado almoxarifado = this.almoxarifadoService.criaAlmoxarifado();


    }
}
