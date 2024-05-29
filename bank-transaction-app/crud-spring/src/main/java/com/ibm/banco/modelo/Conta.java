package com.ibm.banco.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "IBM_Conta")
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d{5}-\\d", message = "O número da conta deve ter 5 dígitos seguido de um dígito verificador")
    @NotBlank(message = "O número da conta é obrigatório")
    private String numeroConta;

    private BigDecimal saldo;

    @OneToOne
    @JsonIgnoreProperties("conta")
    private Cliente cliente;
}
