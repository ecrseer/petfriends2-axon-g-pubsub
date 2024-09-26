package gj.infnet.transportegjpetfriends.infra;

import java.util.UUID;

public class IdUnico {
    public static String criar(){
        return UUID.randomUUID().toString();
    }
}
