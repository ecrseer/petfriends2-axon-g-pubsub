package gj.infnet.almoxarifadogjpetfriends.events;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@Data
public class CriadoAlmoxarifado {
    private final String almoxarifadoId;
    private final Produto produto;
}
