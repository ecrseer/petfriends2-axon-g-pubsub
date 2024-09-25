package gj.infnet.almoxarifadogjpetfriends;

import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
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


    private List<Produto> mockProdutos = List.of(
            new Produto(IdUnico.criar(),"Goiaba",42D),
            new Produto("26","Mamao",2300D),
            new Produto("27","Jujuba",14D),
            new Produto("28","Bombom",44D),
            new Produto("29","Pipoca",12D),
            new Produto("30","Cachorro",100D),
            new Produto("31","Gato",100D),
            new Produto("32","Papagaio",100D),
            new Produto("33","Pato",100D),
            new Produto("34","Galinha",100D)
            );

    public Pedido mockPedidoFactory(List<Produto> prods) {
        Pedido emAndamento = new Pedido(UUID.randomUUID().toString(),
                Date.from(Instant.now()),
                12L,
                "42",
                Pedido.PedidoStatus.EM_PREPARACAO,
                prods);
        return emAndamento;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("testando");


        Pedido emAndamento = mockPedidoFactory(List.of());
        Almoxarifado almoxarifado = this.almoxarifadoService.criaAlmoxarifado(emAndamento);
        String idAlmoxarifado = almoxarifado.getId();

        this.almoxarifadoService.adicionaProdutos(idAlmoxarifado,this.mockProdutos);

        Pedido pedidoRecebido = mockPedidoFactory(this.mockProdutos.subList(2, 4));
        this.almoxarifadoService.removeProdutos(idAlmoxarifado,pedidoRecebido);

    }
}
