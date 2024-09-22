package gj.infnet.petfriendspedidosgj.controller;


import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.service.PedidoService;
import io.micrometer.core.ipc.http.HttpSender;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoCommandController {

    private final PedidoService pedidoService;
    private final StreamBridge streamBridge;

    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido) {
        return pedidoService.criarPedido(pedido);
    }


    @GetMapping("/preparar")
    public String preparar(){
        streamBridge.send("pedido-em-preparacao-topico", "Pedido-42");
        return "enviado";
    }
}
