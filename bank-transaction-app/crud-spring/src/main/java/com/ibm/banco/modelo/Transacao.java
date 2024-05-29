package com.ibm.banco.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "IBM_Transacao")
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;

    @NotNull(message = "O valor da transação é obrigatório")
    private BigDecimal valor;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime data;

    public Transacao() {

    }
    public Transacao(Conta contaOrigem, Conta contaDestino, BigDecimal valor, LocalDateTime data) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = data;
    }


}
