package com.ibm.banco.repositorio;

import com.ibm.banco.modelo.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransacaoRepositorio extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaOrigemId(Long contaId);
    List<Transacao> findByDataBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
