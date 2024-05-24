package br.com.siqueiradev.backend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.siqueiradev.backend.models.BankAccountEntity;
import br.com.siqueiradev.backend.models.BankTransactionEntity;
import br.com.siqueiradev.backend.models.BankTransactionEntity.TypeTransaction;
import br.com.siqueiradev.backend.repositories.BankAccountRepository;
import br.com.siqueiradev.backend.repositories.BankTransactionRepository;

@Service
public class BankTransactionService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    public void registerDebit(Long accountId, Double value) throws AccountNotFoundException {
        registerTransaction(accountId, value, TypeTransaction.DEBIT);
    }

    public void registerCredit(Long accountId, Double value) throws AccountNotFoundException {
        registerTransaction(accountId, value, TypeTransaction.CREDIT);
    }

    private void registerTransaction(Long accountId, Double value, TypeTransaction accountType) throws AccountNotFoundException {
        
        BankAccountEntity bankAccountEntity = searchAccountById(accountId);
        bankAccountEntity.setBalance(bankAccountEntity.getBalance() + (accountType == TypeTransaction.CREDIT ? value : -value)); // Atualiza saldo com base no tipo
        bankAccountRepository.save(bankAccountEntity);

        BankTransactionEntity bankTransactionEntity = new BankTransactionEntity();
        bankTransactionEntity.setAccountType(accountType);
        bankTransactionEntity.setValue(value);
        bankTransactionEntity.setDate(LocalDate.now());
        bankTransactionEntity.setBankAccountEntity(bankAccountEntity);
        bankTransactionRepository.save(bankTransactionEntity);
    }

    private BankAccountEntity searchAccountById(Long accountId) throws AccountNotFoundException {
        Optional<BankAccountEntity> optionalBankAccount = bankAccountRepository.findById(accountId);
        if (!optionalBankAccount.isPresent()) {
            throw new AccountNotFoundException("Account not found with ID: " + accountId);
        }
        return optionalBankAccount.get();
    }

    public List<BankTransactionEntity> consultExtract(Long accountId) throws AccountNotFoundException {
        BankAccountEntity bankAccountEntity = searchAccountById(accountId);
        return bankTransactionRepository.findByBankAccountEntity(bankAccountEntity);
    }
}
