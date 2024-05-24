package br.com.siqueiradev.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.siqueiradev.backend.models.ClientEntity;
import br.com.siqueiradev.backend.repositories.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public List<ClientEntity> listClients() {
        return clientRepository.findAll();
    }

    @PostMapping("/createClient")
    public ClientEntity createClient(@RequestBody ClientEntity client) {
        return clientRepository.save(client);
    }

    // Implemente os métodos para atualizar, excluir e buscar um cliente pelo ID
}