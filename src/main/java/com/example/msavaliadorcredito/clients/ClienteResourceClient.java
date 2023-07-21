package com.example.msavaliadorcredito.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.msavaliadorcredito.models.msclientes.DadosCliente;

@FeignClient(name = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "bi")
    DadosCliente getClienteByBi(@RequestParam("bi") String bi);

}
