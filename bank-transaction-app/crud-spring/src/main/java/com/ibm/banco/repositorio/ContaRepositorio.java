package com.ibm.banco.repositorio;

import com.ibm.banco.modelo.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepositorio extends JpaRepository<Conta, Long> {

}
