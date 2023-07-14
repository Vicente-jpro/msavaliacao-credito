package com.example.msavaliadorcredito.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliador-credito")
public class avalicaoCredito {

    @GetMapping
    public String testController() {
        return "Hello word";
    }

}
