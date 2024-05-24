package br.com.siqueiradev.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.siqueiradev.backend.exceptions.ClientNotFoundException;
import br.com.siqueiradev.backend.models.ClientEntity;
import br.com.siqueiradev.backend.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clienteRepository;

    // ... outros atributos e métodos (se necessário)

    public void registerNewClient(ClientEntity clienteEntity) {
        // Validações e regras de negócio específicas
        clienteRepository.save(clienteEntity);
    }

    public ClientEntity searchClientById(Long id) throws ClientNotFoundException {
        Optional<ClientEntity> optionalClient = clienteRepository.findById(id);

        if (!optionalClient.isPresent()) {
            throw new ClientNotFoundException("Client with ID " + id + " not found"); // Add a message for better
                                                                                      // debugging
        }

        return optionalClient.get();
    }

    public void updateClientData(ClientEntity clienteEntity) {
        // Validações e regras de negócio específicas
        clienteRepository.save(clienteEntity);
    }

    public void deleteClient(Long id) {
        clienteRepository.deleteById(id);
    }
}
