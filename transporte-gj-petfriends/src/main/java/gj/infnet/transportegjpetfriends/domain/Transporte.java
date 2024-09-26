package gj.infnet.transportegjpetfriends.domain;

import gj.infnet.transportegjpetfriends.command.EnviarPedidoEmTransitoCommand;
import gj.infnet.transportegjpetfriends.events.PedidoEnviadoEmTransito;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Aggregate
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transporte {
    @Id
    @AggregateIdentifier
    private String id;
    private String status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String responsavelEntrega;
    private String clienteId;
    private double pesoTotal;
    private List<String> pedidosIds;
    private List<String> produtosIds;
    private int duracaoHoras;

    private TransporteEndereco endereco;

    @CommandHandler
    public Transporte(EnviarPedidoEmTransitoCommand comando) {
        AggregateLifecycle.apply(new PedidoEnviadoEmTransito(
                comando.getId(),
                comando.getProdutos(),
                comando.getEndereco()
        ));
    }

    @EventSourcingHandler
    protected void on(PedidoEnviadoEmTransito evento) {
        this.id = evento.getId();
        if (!evento.getEndereco()) {
            throw new IllegalStateException("Endereco nao pode ser vazio");
        }
        this.endereco = evento.getEndereco();
        this.produtosIds = evento.getProdutosId();
    }

    private int calculaRotaDuracao(Endereco endereco){
        return 42;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public String getResponsavelEntrega() {
        return responsavelEntrega;
    }

    public void setResponsavelEntrega(String responsavelEntrega) {
        this.responsavelEntrega = responsavelEntrega;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public List<String> getPedidosIds() {
        return pedidosIds;
    }

    public void setPedidosIds(List<String> pedidosIds) {
        this.pedidosIds = pedidosIds;
    }
}
