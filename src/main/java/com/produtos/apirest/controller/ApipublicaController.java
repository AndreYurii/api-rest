package com.produtos.apirest.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
@Api(value = "API REST")
@CrossOrigin(origins = "*")
public class ApipublicaController {

    @GetMapping()
    public String meuServicoPublico() {
        return "https://github.com/AndreYurii/api-rest";
    }
}
