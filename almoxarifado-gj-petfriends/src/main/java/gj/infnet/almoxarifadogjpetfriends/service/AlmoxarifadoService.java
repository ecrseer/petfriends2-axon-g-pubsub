package gj.infnet.almoxarifadogjpetfriends.service;

import gj.infnet.almoxarifadogjpetfriends.command.AdicionarProdutosAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.AlterarAlmoxarifadoCommand;
import gj.infnet.almoxarifadogjpetfriends.command.CriarAlmoxarifadoCommand;

import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import gj.infnet.almoxarifadogjpetfriends.infra.external.Pedido;
import gj.infnet.almoxarifadogjpetfriends.infra.MensagemGPub;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AlmoxarifadoService {

    private final CommandGateway commandGateway;
    private final AlmoxarifadoRepository almoxarifadoRepository;

    public Consumer<Message<MensagemGPub>> pedidoEmPreparacaoTopicoSub() {
        return mensagem -> {
            Pedido pedido = (Pedido) mensagem.getPayload().getValor();
//            this.criaAlmoxarifado(pedido);
        };
    }

    public void receberPedidoEmPreparacao(Pedido pedido) {
        String almoxarifado = this.criaAlmoxarifado(pedido);
        this.mockAdicionaProdutos(almoxarifado);

    }

    public String criaAlmoxarifado(Pedido pedido) {
        String idAlmoxarifado = "42asd";
        commandGateway.send(
                new CriarAlmoxarifadoCommand(idAlmoxarifado,
                        pedido.getProdutos())
        );
        this.obterAlmoxarife(idAlmoxarifado);
        return idAlmoxarifado;
    }

    public Almoxarifado obterAlmoxarife (String idAlmoxarifado) {
        Optional<Almoxarifado> byId = almoxarifadoRepository.findById(idAlmoxarifado);
        if (byId.isEmpty()) {
            throw new IllegalStateException("N encontrei");
        } else {
            Almoxarifado almoxarifado = byId.get();
            Produto produtoEnc = almoxarifado.getProdutos().get(0);
            System.out.println(produtoEnc.getNome());
            System.out.println(produtoEnc.getId());
            return almoxarifado;
        }
    }


    public void mockAdicionaProdutos(String idAlmoxarifado) {
        List<Produto> produtos = List.of(
                new Produto(IdUnico.criar(),"Mamao",2300D),
                new Produto(IdUnico.criar(),"Jujuba",14D)
        );
        this.adicionaProdutos(idAlmoxarifado,produtos);
    }
    public void adicionaProdutos(String idAlmoxarifado, List<Produto> produto) {
        commandGateway.send(new AdicionarProdutosAlmoxarifadoCommand(idAlmoxarifado, produto));
        Almoxarifado almoxarifado = obterAlmoxarife(idAlmoxarifado);
        almoxarifado.setProdutos(List.of());
        System.out.println(almoxarifado);
        Almoxarifado almoxarifadoRecuperado = obterAlmoxarife(idAlmoxarifado);

        if(almoxarifado.getProdutos().size()<2){
//            throw new IllegalStateException("n adicionou");
        }

    }

    public void mockAdicionaNome(String almoxarifado){
        this.commandGateway.send(new AlterarAlmoxarifadoCommand(almoxarifado,"Uepa"));
        Almoxarifado almoxarifado1 = obterAlmoxarife(almoxarifado);
        if(almoxarifado1 != null) {
            System.out.println(almoxarifado1);
        }
    }


}
