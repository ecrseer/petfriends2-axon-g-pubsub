package gj.infnet.almoxarifadogjpetfriends.command;


import gj.infnet.almoxarifadogjpetfriends.domain.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@Data
@ToString
public class CriarAlmoxarifadoCommand {
    private final String almoxarifadoId;
    private final Produto produto;
}
