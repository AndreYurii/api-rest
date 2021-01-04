package com.produtos.apirest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @NotBlank(message = "{name.not.blank}")
    private String nome;

    @Email(message = "{email.not.valid}")
    private String email;

    private String sexo;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    private String naturalidade;
    private String nacionalidade;

    @CPF(message = "{cpf.not.valid}")
    private String cpf;

    private LocalDate dataDoCadastramento;
    private LocalDate dataDaAtualizacaoCadastral;

}
