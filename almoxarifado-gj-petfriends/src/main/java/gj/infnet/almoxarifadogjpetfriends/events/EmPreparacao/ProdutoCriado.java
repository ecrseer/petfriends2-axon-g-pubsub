package gj.infnet.almoxarifadogjpetfriends.events.EmPreparacao;

import gj.infnet.almoxarifadogjpetfriends.events.Evento;

public class ProdutoCriado {
    public String id;
    public String nomeProduto;
    public ProdutoCriado(String id, String nomeProduto) {
        this.id = id;
        this.nomeProduto = nomeProduto;
    }
}
