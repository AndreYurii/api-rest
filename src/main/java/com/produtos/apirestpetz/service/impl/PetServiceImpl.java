package com.produtos.apirestpetz.service.impl;

import com.produtos.apirestpetz.dto.PetDTO;
import com.produtos.apirestpetz.model.Cliente;
import com.produtos.apirestpetz.model.Pet;
import com.produtos.apirestpetz.repository.PetRepository;
import com.produtos.apirestpetz.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository repository;

    @Override
    public List<Pet> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Pet buscarPorId(long id) {
        return repository.findById(id);
    }

    @Override
    public Pet salvar(PetDTO petDTO) throws Exception {
        String nome = petDTO.getNome();
        Boolean nomeBanco = repository.existsByNome(nome);
        if(nomeBanco){
            throw new Exception("Email do cliente já cadastrado");
        } else {
            Pet pet = new Pet();
            pet.setNome(petDTO.getNome());
            pet.setRaca(petDTO.getRaca());
            pet.setIdade(petDTO.getIdade());
            return repository.save(pet);
        }
    }

    @Override
    public Pet deletar(long id) {
        return repository.deleteById(id);
    }

    @Override
    public Pet atualizar(Pet pet) {
        return repository.save(pet);
    }
}
