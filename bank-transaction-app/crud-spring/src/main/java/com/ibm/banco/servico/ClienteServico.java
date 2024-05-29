package com.ibm.banco.servico;

import com.ibm.banco.modelo.Cliente;
import com.ibm.banco.modelo.Conta;
import com.ibm.banco.repositorio.ClienteRepositorio;
import com.ibm.banco.repositorio.ContaRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ContaRepositorio contaRepositorio;

    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long clienteId) {
        return clienteRepositorio.findById(clienteId);
    }

    public Cliente salvarClienteComConta(@NotNull @Valid Cliente cliente) {
        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Conta conta = cliente.getConta();
        conta.setCliente(clienteSalvo);
        contaRepositorio.save(conta);

        return clienteSalvo;
    }

    public Cliente editarClienteComConta(Long id, @NotNull @Valid Cliente cliente) {
        Cliente clienteExistente = buscarClientePorId(id).orElseThrow(() -> new ValidationException("Cliente não encontrado"));

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setIdade(cliente.getIdade());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.getConta().setNumeroConta(cliente.getConta().getNumeroConta());
        clienteExistente.getConta().setSaldo(cliente.getConta().getSaldo());

        Cliente clienteAtualizado = clienteRepositorio.save(clienteExistente);

        return clienteAtualizado;
    }

    public void excluirCliente(Long clienteId) {
        clienteRepositorio.deleteById(clienteId);
    }

    public void verificaContaExistente() {
        
    }
}
