package com.produtos.apirestpetz.service.impl;

import com.produtos.apirestpetz.dto.ClienteDTO;
import com.produtos.apirestpetz.model.Cliente;
import com.produtos.apirestpetz.repository.ClienteRepository;
import com.produtos.apirestpetz.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository repository;

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Cliente buscarPorId(long id) {
        return repository.findById(id);
    }

    @Override
    public Cliente salvar(ClienteDTO clienteDTO) throws Exception {
        String email = clienteDTO.getEmail();
        Boolean emailBanco = repository.existsByEmail(email);
        if(emailBanco){
            throw new Exception("Email do cliente já cadastrado");
        } else {
            Cliente cliente = new Cliente();
            cliente.setNome(clienteDTO.getNome());
            cliente.setEmail(clienteDTO.getEmail());
            return repository.save(cliente);
        }
    }

    @Override
    public Cliente buscaPorEmail(String email) throws Exception {
        Cliente emailDoCliente = repository.findByEmail(email);
        if(emailDoCliente != null){
            return repository.findByEmail(email);
        } else {
            throw new Exception("Email do cliente não encontrado");
        }
    }

    @Override
    public Cliente deletar(long id) {
        return repository.deleteById(id);
    }

    @Override
    public Cliente atualizar(Cliente clienteDTO) {
        return null;
    }

}
