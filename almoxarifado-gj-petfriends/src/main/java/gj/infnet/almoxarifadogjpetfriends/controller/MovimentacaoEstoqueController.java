package gj.infnet.almoxarifadogjpetfriends.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("olamundo")
public class MovimentacaoEstoqueController {

    @GetMapping
    public String ola(){
        return "olamundo4242";
    }
}
