package com.produtos.apirestpetz.controller;

import com.produtos.apirestpetz.dto.PetDTO;
import com.produtos.apirestpetz.model.Pet;
import com.produtos.apirestpetz.service.PetService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pet")
@Api(value = "API REST Pet")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    PetService service;

    @GetMapping()
    public ResponseEntity<List<Pet>> buscarTodos(){
        List<Pet> pets = service.buscarTodos();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPorId(@PathVariable(value = "id") long id){
        Pet pet = service.buscarPorId(id);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Pet> salvarPet(@RequestBody PetDTO clienteDTO) throws Exception {
        service.salvar(clienteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPet(@PathVariable long id) {
        service.deletar(id);
        return new ResponseEntity<>("Deletado com sucesso",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> atualizarPessoa(@PathVariable ("id") long id, @RequestBody Pet novoPet) throws Exception {
        Optional<Pet> antigoPet = Optional.ofNullable(service.buscarPorId(id));
        if(antigoPet.isPresent()){
            Pet pet = antigoPet.get();
            pet.setNome(novoPet.getNome());
            pet.setRaca(novoPet.getRaca());
            pet.setIdade(novoPet.getIdade());
            service.atualizar(pet);
            return new ResponseEntity<>(novoPet, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
