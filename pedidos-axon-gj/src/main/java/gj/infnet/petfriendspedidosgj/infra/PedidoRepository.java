package gj.infnet.petfriendspedidosgj.infra;

import gj.infnet.petfriendspedidosgj.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
}
