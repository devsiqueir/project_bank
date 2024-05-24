package br.com.siqueiradev.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siqueiradev.backend.models.ClientEntity;


public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}