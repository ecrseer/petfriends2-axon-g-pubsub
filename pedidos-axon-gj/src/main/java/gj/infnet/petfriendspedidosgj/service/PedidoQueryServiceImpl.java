package gj.infnet.petfriendspedidosgj.service;

import gj.infnet.petfriendspedidosgj.domain.Pedido;
import gj.infnet.petfriendspedidosgj.infra.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoQueryServiceImpl {

    private final EventStore eventStore;
    private final PedidoRepository pedidoRepository;

    public List<Object> listarEventosPorId(String id){
        var streamEventos = eventStore.readEvents(id,0)
                .asStream();
        return streamEventos.map(evento->evento.getPayload())
                .collect(Collectors.toList());
    }

    public Optional<Pedido> encontraPedidoPorId(String uid){
        return this.pedidoRepository.findById(uid);
    }
    public List<Pedido> obtemTodosPedidos(){
        return this.pedidoRepository.findAll();
    }

}
