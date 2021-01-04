package com.produtos.apirest.service.impl;

import com.produtos.apirest.dto.ClienteDTO;
import com.produtos.apirest.model.Cliente;
import com.produtos.apirest.repository.ClienteRepository;
import com.produtos.apirest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
        validaCpf(clienteDTO);
        validaEmail(clienteDTO);
        Cliente cliente = new Cliente();
            cliente.setNome(clienteDTO.getNome());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setSexo(clienteDTO.getSexo());
            cliente.setNacionalidade(clienteDTO.getNacionalidade());
            cliente.setNaturalidade(clienteDTO.getNaturalidade());
            cliente.setDataDoCadastramento(LocalDate.now());
            cliente.setDataDeNascimento(clienteDTO.getDataDeNascimento());
            return repository.save(cliente);
    }

    @Override
    public Cliente deletar(long id) {
        return repository.deleteById(id);
    }

    @Override
    public Cliente atualizar(ClienteDTO clienteDTO, long id) throws Exception {
        Optional<Cliente> antigoCliente = Optional.ofNullable(repository.findById(id));
        if(antigoCliente.isPresent()){
            Cliente cliente = antigoCliente.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setSexo(clienteDTO.getSexo());
            cliente.setNacionalidade(clienteDTO.getNacionalidade());
            cliente.setNaturalidade(clienteDTO.getNaturalidade());
            cliente.setDataDoCadastramento(clienteDTO.getDataDoCadastramento());
            cliente.setDataDeNascimento(clienteDTO.getDataDeNascimento());
            cliente.setDataDaAtualizacaoCadastral(LocalDate.now());

            return repository.save(cliente);
        } else throw new Exception("cliente.ts não existente");
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

    private void validaCpf(ClienteDTO clienteDTO) throws Exception {
        Boolean cpf = repository.existsByCpf(clienteDTO.getCpf());
        if(cpf){
            throw new Exception("CPF já cadastrado");
        }
    }
    private void validaEmail(ClienteDTO clienteDTO) throws Exception {
        Boolean emailBanco = repository.existsByEmail(clienteDTO.getEmail());
        if (emailBanco) {
            throw new Exception("Email já cadastrado");
        }
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

}
