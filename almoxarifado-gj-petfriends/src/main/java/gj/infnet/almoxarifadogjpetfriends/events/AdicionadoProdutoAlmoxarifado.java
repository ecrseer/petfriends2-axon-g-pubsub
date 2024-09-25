package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Getter;

@Getter
public class AdicionadoProdutoAlmoxarifado extends Evento {
    private final Produto produto;

    public AdicionadoProdutoAlmoxarifado(String id, Produto produto) {
        super(id);
        this.produto = produto;
    }
}
