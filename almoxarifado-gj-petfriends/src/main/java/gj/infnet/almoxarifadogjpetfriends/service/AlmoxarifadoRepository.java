package gj.infnet.almoxarifadogjpetfriends.service;


import gj.infnet.almoxarifadogjpetfriends.domain.Almoxarifado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmoxarifadoRepository extends JpaRepository<Almoxarifado, String> {

    Almoxarifado findAlmoxarifadoByEndereco_Cidade(String cidade);
}
