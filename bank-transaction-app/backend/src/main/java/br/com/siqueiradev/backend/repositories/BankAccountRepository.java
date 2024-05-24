package br.com.siqueiradev.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siqueiradev.backend.models.BankAccountEntity;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {


}