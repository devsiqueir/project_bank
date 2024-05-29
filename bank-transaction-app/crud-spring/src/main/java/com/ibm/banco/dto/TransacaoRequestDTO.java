package com.ibm.banco.dto;

import com.ibm.banco.modelo.Transacao;
import lombok.Data;

import java.math.BigDecimal;

@Data public class TransacaoRequestDTO {

    private Long contaOrigemId;
    private Long contaDestinoId;
    private BigDecimal valor;

    public static TransacaoRequestDTO toDto(Transacao entidade) {
        TransacaoRequestDTO dto = new TransacaoRequestDTO();
        dto.setContaOrigemId(entidade.getContaOrigem().getId());
        dto.setContaDestinoId(entidade.getContaDestino().getId());
        dto.setValor(entidade.getValor());

        return dto;
    }
}
