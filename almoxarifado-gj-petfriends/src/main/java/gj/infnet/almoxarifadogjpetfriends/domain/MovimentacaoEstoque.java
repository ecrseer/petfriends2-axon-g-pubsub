package gj.infnet.almoxarifadogjpetfriends.domain;


import gj.infnet.almoxarifadogjpetfriends.command.EnviarPedidoEmPreparacaoCommand;
import gj.infnet.almoxarifadogjpetfriends.events.EnviadoPedidoEmPreparacao;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoEstoque implements Serializable {

    @Id
    private String id;

    private String tipo;

    public enum EnumTipoMovimentacaoEstoque {
        RECEBIDO, ENVIADO
    }

    private int quantidade;

    @OneToOne
    private Produto produto;
    @OneToOne
    private Almoxarifado almoxarifado;
    private String descricao;
    private Date data;

    public MovimentacaoEstoque(String tipo,
                               int quantidade,
                               Produto produto,
                               Almoxarifado almoxarifado,
                               String descricao,
                               Date data) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.produto = produto;
        this.almoxarifado = almoxarifado;
        this.descricao = descricao;
        this.data = data;
    }
}
