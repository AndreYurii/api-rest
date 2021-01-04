package com.produtos.apirest.controller;

import com.produtos.apirest.dto.ClienteDTO;
import com.produtos.apirest.model.Cliente;
import com.produtos.apirest.service.ClienteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@Api(value = "API REST cliente.ts")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping()
    public ResponseEntity<List<Cliente>> buscarTodos() {
        List<Cliente> clientes = service.buscarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(value = "id") long id) {
        Cliente cliente = service.buscarPorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> salvarPessoa(@RequestBody @Valid ClienteDTO clienteDTO) throws Exception {
        try {
            service.salvar(clienteDTO);
            return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable long id) {
        service.deletar(id);
        return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable("id") long id, @RequestBody @Valid ClienteDTO novoCliente) throws Exception {
        try {
            Cliente cliente = service.atualizar(novoCliente, id);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}