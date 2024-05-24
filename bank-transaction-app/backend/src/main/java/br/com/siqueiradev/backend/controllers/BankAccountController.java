package br.com.siqueiradev.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.siqueiradev.backend.models.BankAccountEntity;
import br.com.siqueiradev.backend.models.BankTransactionEntity;
import br.com.siqueiradev.backend.services.BankTransactionService;
import br.com.siqueiradev.backend.services.BankAccountService;
import br.com.siqueiradev.backend.models.ClientEntity;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankTransactionService bankTransactionService;
    
    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/createAccount")
    public ResponseEntity<BankAccountEntity> createAccount(@RequestBody BankAccountEntity bankAccountEntity) {
        BankAccountEntity createdAccount = bankAccountService.createAccount(bankAccountEntity);
        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/createNewAccount")
    public ResponseEntity<Void> createNewAccount(ClientEntity clientEntity) {
        bankAccountService.createNewAccount(clientEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<List<BankTransactionEntity>> consultExtract(@PathVariable Long accountId) throws javax.security.auth.login.AccountNotFoundException {
        List<BankTransactionEntity> balance = bankTransactionService.consultExtract(accountId);
        return ResponseEntity.ok(balance);
    }
}
