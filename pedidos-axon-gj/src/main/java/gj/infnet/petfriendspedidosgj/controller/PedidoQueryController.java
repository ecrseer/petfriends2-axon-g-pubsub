package gj.infnet.petfriendspedidosgj.controller;


import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.service.PedidoQueryServiceImpl;
import gj.infnet.petfriendspedidosgj.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoQueryController {

    private final PedidoQueryServiceImpl pedidoService;

    @GetMapping("/{id}/eventos")
    public List<Object> obterTodosEventos(@PathVariable(value = "id") String id){
        return pedidoService.listarEventosPorId(id);
    }

    @GetMapping("/{id}")
    public Pedido obterPorId(@PathVariable(value = "id") String id){
        try{
            Optional<Pedido> pedido = pedidoService.encontraPedidoPorId(id);
            if(pedido.isPresent()){
                return pedido.get();
            }
            throw new RuntimeException("Pedido nao encontrado");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
