package br.com.siqueiradev.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.siqueiradev.backend.exceptions.BankNotFoundException;
import br.com.siqueiradev.backend.models.BankAccountEntity;
import br.com.siqueiradev.backend.models.ClientEntity;
import br.com.siqueiradev.backend.repositories.BankAccountRepository;
import br.com.siqueiradev.backend.repositories.ClientRepository;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public BankAccountEntity createAccount(BankAccountEntity bankAccountEntity) {
        ClientEntity clientEntity = bankAccountEntity.getClientEntity();
        if (clientEntity != null && clientEntity.getId() == null) {
            // Salva a entidade ClientEntity se ainda não estiver persistida
            clientEntity = clientRepository.save(clientEntity);
        }
        bankAccountEntity.setClientEntity(clientEntity);
        return bankAccountRepository.save(bankAccountEntity);
    }

    @Transactional
    public void createNewAccount(ClientEntity clientEntity) {
        if (clientEntity.getId() == null) {
            // Salva a entidade ClientEntity se ainda não estiver persistida
            clientEntity = clientRepository.save(clientEntity);
        }
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setBalance(0.0); // Saldo inicial zero
        bankAccountEntity.setClientEntity(clientEntity);
        bankAccountRepository.save(bankAccountEntity);
    }

    public BankAccountEntity searchAccountById(Long id) throws BankNotFoundException {
        Optional<BankAccountEntity> optionalBank = bankAccountRepository.findById(id);
        if (!optionalBank.isPresent()) {
            throw new BankNotFoundException("Bank with ID " + id + " not found");
        }
        return optionalBank.get();
    }

    @Transactional
    public void updateAccountBalance(Long accountId, Double balance) throws BankNotFoundException {
        BankAccountEntity bankAccountEntity = searchAccountById(accountId);
        bankAccountEntity.setBalance(bankAccountEntity.getBalance() + balance);
        bankAccountRepository.save(bankAccountEntity);
    }
}
