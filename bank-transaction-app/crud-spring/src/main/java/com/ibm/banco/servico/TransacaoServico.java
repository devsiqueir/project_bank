package com.ibm.banco.servico;

import com.ibm.banco.dto.TransacaoRequestDTO;
import com.ibm.banco.modelo.Conta;
import com.ibm.banco.modelo.Transacao;
import com.ibm.banco.repositorio.ContaRepositorio;
import com.ibm.banco.repositorio.TransacaoRepositorio;
import com.ibm.banco.util.TransacoesFinanceirasUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
public class TransacaoServico {

    @Autowired
    private TransacaoRepositorio transacaoRepositorio;

    @Autowired
    private ContaRepositorio contaRepositorio;

    public List<Transacao> obterExtratoContaCliente(Long contaId) {
        return transacaoRepositorio.findByContaOrigemId(contaId);
    }
    public List<Transacao> obterRelatorioTransacoesDoDia(LocalDate data) {
        LocalDateTime dataInicio = data.atStartOfDay();
        LocalDateTime dataFim = dataInicio.plusDays(1).minusNanos(1);
        return transacaoRepositorio.findByDataBetween(dataInicio, dataFim);
    }

    public TransacaoRequestDTO realizarTransferencia(TransacaoRequestDTO dto) {

        Conta origem = contaRepositorio.getById(dto.getContaOrigemId());
        Conta destino = contaRepositorio.getById(dto.getContaDestinoId());
        Transacao transacao = new Transacao(origem, destino, dto.getValor(), ZonedDateTime.now().toLocalDateTime());

        TransacoesFinanceirasUtil.transferirValor(origem, destino, dto.getValor());

        contaRepositorio.save(origem);
        contaRepositorio.save(destino);
        transacaoRepositorio.save(transacao);

        return TransacaoRequestDTO.toDto(transacao);
    }
}
