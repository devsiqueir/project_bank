package br.com.siqueiradev.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siqueiradev.backend.models.BankAccountEntity;
import br.com.siqueiradev.backend.models.BankTransactionEntity;

public interface BankTransactionRepository extends JpaRepository<BankTransactionEntity, Long> {
    List<BankTransactionEntity> findByBankAccountEntity(BankAccountEntity bankAccountEntity);
}