package gj.infnet.almoxarifadogjpetfriends.events;

import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Getter;

@Getter
public class RemovidoProdutoAlmoxarifado extends Evento {
    private final Produto produto;

    public RemovidoProdutoAlmoxarifado(String id, Produto produto) {
        super(id);
        this.produto = produto;
    }
}
