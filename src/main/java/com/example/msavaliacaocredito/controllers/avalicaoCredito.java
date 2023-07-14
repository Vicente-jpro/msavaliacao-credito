package com.example.msavaliacaocredito.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacao-credito")
public class avalicaoCredito {

    @GetMapping
    public String testController() {
        return "Hello word";
    }

}
