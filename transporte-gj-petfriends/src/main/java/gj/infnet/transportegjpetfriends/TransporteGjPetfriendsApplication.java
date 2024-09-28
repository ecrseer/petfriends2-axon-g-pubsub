package gj.infnet.transportegjpetfriends;

import gj.infnet.transportegjpetfriends.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransporteGjPetfriendsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TransporteGjPetfriendsApplication.class, args);
    }

    @Autowired
    private TransporteService transporteService;

    @Override
    public void run(String... args) throws Exception {
        this.transporteService.criarTransporte();
    }
}
