package com.ibm.banco.util;

import com.ibm.banco.modelo.Conta;

import java.math.BigDecimal;

public class TransacoesFinanceirasUtil {

    public static void transferirValor(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        if (contaOrigem == null || contaDestino == null || valor == null) {
            throw new IllegalArgumentException("As contas e o valor devem ser fornecidos.");
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
        }

        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente na conta de origem para realizar a transferência.");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        
    }

}
