package com.produtos.apirestpetz.repository;

import com.produtos.apirestpetz.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findById(long id);
    Pet deleteById(long id);
    Boolean existsByNome(String nome);
}
