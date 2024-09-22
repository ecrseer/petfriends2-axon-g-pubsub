package gj.infnet.almoxarifadogjpetfriends.domain;

import jakarta.persistence.OneToMany;

import java.util.List;

public class Almoxarifado {

    String id;
    Endereco endereco;
    List<Produto> produtos;
}
