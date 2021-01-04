package com.produtos.apirest.repository;

import com.produtos.apirest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findById(long id);
    Cliente deleteById(long id);
    Cliente findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);
}
