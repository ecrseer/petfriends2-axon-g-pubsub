package gj.infnet.almoxarifadogjpetfriends.infra.external;

import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import gj.infnet.almoxarifadogjpetfriends.infra.IdUnico;

import java.util.List;

public class MocksData {
    public static List<Produto> petProdutos = List.of(
            new Produto(IdUnico.criar(), "Bolinha de Morder", 42D),
            new Produto("26", "Ração para Cães", 2300D),
            new Produto("27", "Ração para Gatos", 14D),
            new Produto("28", "Coleira", 44D),
            new Produto("29", "Petisco para Cães", 12D),
            new Produto("30", "Caminha para Cães", 100D),
            new Produto("31", "Caminha para Gatos", 100D),
            new Produto("32", "Brinquedo para Pássaros", 100D),
            new Produto("33", "Comedouro para Cães", 100D),
            new Produto("34", "Bebedouro Automático", 100D)
    );
}
