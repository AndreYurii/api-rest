package com.produtos.apirestpetz.controller;

import com.produtos.apirestpetz.dto.ClienteDTO;
import com.produtos.apirestpetz.model.Cliente;
import com.produtos.apirestpetz.service.ClienteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@Api(value = "API REST Cliente")
@CrossOrigin(origins = "*")
public class ClienteController  {

    @Autowired
    ClienteService service;

    @GetMapping()
    public ResponseEntity<List<Cliente>> buscarTodos(){
        List<Cliente> clientes = service.buscarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(value = "id") long id){
        Cliente cliente = service.buscarPorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Cliente> salvarPessoa(@RequestBody ClienteDTO clienteDTO) throws Exception {
        service.salvar(clienteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable long id) {
        service.deletar(id);
        return new ResponseEntity<>("Deletado com sucesso",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarPessoa(@PathVariable ("id") long id, @RequestBody Cliente novoCliente) throws Exception {
        Optional<Cliente> antigoCliente = Optional.ofNullable(service.buscarPorId(id));
        if(antigoCliente.isPresent()){
            Cliente cliente = antigoCliente.get();
            cliente.setNome(novoCliente.getNome());
            cliente.setEmail(novoCliente.getEmail());
            service.atualizar(cliente);
            return new ResponseEntity<>(novoCliente, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
