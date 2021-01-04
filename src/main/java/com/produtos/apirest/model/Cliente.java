package com.produtos.apirest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produtos.apirest.util.DateUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String email;
    private String sexo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;
    private LocalDate dataDoCadastramento;
    private LocalDate dataDaAtualizacaoCadastral;

}
