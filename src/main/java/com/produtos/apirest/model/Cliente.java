package com.produtos.apirest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String email;
    private String sexo;
    private LocalDate dataDeNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;
    private LocalDate dataDoCadastramento;
    private LocalDate dataDaAtualizacaoCadastral;

}
