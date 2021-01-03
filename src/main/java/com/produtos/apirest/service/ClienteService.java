package com.produtos.apirest.service;

import com.produtos.apirest.dto.ClienteDTO;
import com.produtos.apirest.model.Cliente;

import java.util.List;

public interface ClienteService  {

    List<Cliente> buscarTodos();
    Cliente buscarPorId(long id);
    Cliente salvar(ClienteDTO cliente) throws Exception;
    Cliente deletar(long id);
    Cliente atualizar(ClienteDTO clienteDTO, long id) throws Exception;
    Cliente buscaPorEmail(String email) throws Exception;
}
