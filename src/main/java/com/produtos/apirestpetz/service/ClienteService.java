package com.produtos.apirestpetz.service;

import com.produtos.apirestpetz.dto.ClienteDTO;
import com.produtos.apirestpetz.model.Cliente;

import java.util.List;

public interface ClienteService  {

    List<Cliente> buscarTodos();
    Cliente buscarPorId(long id);
    Cliente salvar(ClienteDTO cliente) throws Exception;
    Cliente deletar(long id);
    Cliente atualizar(Cliente clienteDTO);
    Cliente buscaPorEmail(String email) throws Exception;
}
