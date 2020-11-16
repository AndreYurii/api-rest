package com.produtos.apirestpetz.service;

import com.produtos.apirestpetz.dto.ClienteDTO;
import com.produtos.apirestpetz.dto.PetDTO;
import com.produtos.apirestpetz.model.Cliente;
import com.produtos.apirestpetz.model.Pet;

import java.util.List;

public interface PetService {

    List<Pet> buscarTodos();
    Pet buscarPorId(long id);
    Pet salvar(PetDTO petDTO) throws Exception;
    Pet deletar(long id);
    Pet atualizar(Pet pet);
}
