package gj.infnet.almoxarifadogjpetfriends.service;

import gj.infnet.almoxarifadogjpetfriends.command.AdicionarProdutosAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.AlterarAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.CriarAlmoxarifadoCommand;

import gj.infnet.almoxarifadogjpetfriends.command.EnviarPedidoEmPreparacaoCommand;
import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.infra.MensagemGPub;
import gj.infnet.almoxarifadogjpetfriends.infra.external.PedidoDto;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AlmoxarifadoService {

    private final CommandGateway commandGateway;
    private final AlmoxarifadoRepository almoxarifadoRepository;


    @Bean
    public Consumer<Message<MensagemGPub>> pedidoEmPreparacaoTopicoSub() {
        return mensagem -> {
            LinkedHashMap json = (LinkedHashMap) mensagem.getPayload().getValor();
            Pedido fact = new Pedido().fact(json);
            System.out.println(fact);
//            Almoxarifado almoxarifado = this.criaAlmoxarifado(pedido);

//            this.removeProdutos(almoxarifado.getId(),pedido);

        };
    }


    public Almoxarifado criaAlmoxarifado(Pedido pedido) {
        String idAlmoxarifado = IdUnico.criar();
        commandGateway.send(
                new CriarAlmoxarifadoCommand(idAlmoxarifado,
                        pedido.getProdutos())
        );
        this.adicionaProdutos(idAlmoxarifado,mockProdutos);
        return this.obterAlmoxarife(idAlmoxarifado);
    }

    public Almoxarifado obterAlmoxarife (String idAlmoxarifado) {
        Optional<Almoxarifado> byId = almoxarifadoRepository.findById(idAlmoxarifado);
        if (byId.isEmpty()) {
            throw new IllegalStateException("N encontrei");
        } else {
            Almoxarifado almoxarifado = byId.get();
            return almoxarifado;
        }
    }

    public void adicionaProdutos(String idAlmoxarifado, List<Produto> produto) {
        commandGateway.send(new AdicionarProdutosAlmoxarifadoCommand(idAlmoxarifado, produto));
        Almoxarifado almoxarifado = obterAlmoxarife(idAlmoxarifado);
        if(almoxarifado.getProdutos().size()<2){
            throw new IllegalStateException("n adicionou");
        }

    }

    public void removeProdutos(String idAlmoxarifado, Pedido pedido) {
        commandGateway.send(new EnviarPedidoEmPreparacaoCommand(idAlmoxarifado,pedido));
        Almoxarifado almoxarifado1 = obterAlmoxarife(idAlmoxarifado);
        if(almoxarifado1 != null) {
            System.out.println(almoxarifado1);
        }
    }

    public void mockAdicionaNome(String almoxarifado){
        this.commandGateway.send(new AlterarAlmoxarifadoCommand(almoxarifado,"Uepa"));
        Almoxarifado almoxarifado1 = obterAlmoxarife(almoxarifado);
        if(almoxarifado1 != null) {
            System.out.println(almoxarifado1);
        }
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

}
