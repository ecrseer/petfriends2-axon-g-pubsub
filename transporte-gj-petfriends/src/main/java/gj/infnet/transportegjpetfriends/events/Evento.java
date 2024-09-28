package gj.infnet.transportegjpetfriends.events;


import lombok.Getter;

@Getter
public class Evento {

    private final String id;

    public Evento(String id){
        this.id = id;
    }
}
