package gj.infnet.almoxarifadogjpetfriends.command.EmPreparacao;

import gj.infnet.almoxarifadogjpetfriends.command.Comando;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CriarProdutoCommand extends Comando {

    @TargetAggregateIdentifier
    public String id;
    public String nomeProduto;


    public CriarProdutoCommand(String id,String nomeProduto) {
        this.id = id;
        this.nomeProduto = nomeProduto;
    }
}
