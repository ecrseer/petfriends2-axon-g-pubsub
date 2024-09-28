package gj.infnet.transportegjpetfriends.events;


import lombok.Getter;

@Getter
public class IniciadoTransporte extends Evento {
    private final String endereco;

    public IniciadoTransporte(String id, String endereco) {
        super(id);
        this.endereco = endereco;
    }
}
