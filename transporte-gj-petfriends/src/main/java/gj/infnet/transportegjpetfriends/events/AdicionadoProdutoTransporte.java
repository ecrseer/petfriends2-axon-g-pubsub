package gj.infnet.transportegjpetfriends.events;


import lombok.Getter;

@Getter
public class AdicionadoProdutoTransporte extends Evento {
    private final String produtoId;

    public AdicionadoProdutoTransporte(String id, String produtoId) {
        super(id);
        this.produtoId = produtoId;
    }
}
