package br.com.siqueiradev.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.siqueiradev.backend.models.BankTransactionEntity;
import br.com.siqueiradev.backend.services.BankTransactionService;

@RestController
@RequestMapping("/transactions")
public class BankTransactionController {
    @Autowired
    private BankTransactionService bankTransactionService;

    @PostMapping("/{id}/debit/{value}")
    public ResponseEntity<Void> registerDebit(@PathVariable Long accountId, @PathVariable Double value) throws javax.security.auth.login.AccountNotFoundException {
        bankTransactionService.registerDebit(accountId, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/credit/{value}")
    public ResponseEntity<Void> registerCredit(@PathVariable Long accountId, @PathVariable Double value) throws javax.security.auth.login.AccountNotFoundException {
        bankTransactionService.registerCredit(accountId, value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<List<BankTransactionEntity>> consultExtract(@PathVariable Long accountId) throws javax.security.auth.login.AccountNotFoundException {
        List<BankTransactionEntity> balance = bankTransactionService.consultExtract(accountId);
        return ResponseEntity.ok(balance);
    }
}
