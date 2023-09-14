package com.example.msavaliadorcredito.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.msavaliadorcredito.models.mscartoes.Cartao;
import com.example.msavaliadorcredito.models.mscartoes.CartaoCliente;

@FeignClient(name = "mscartoes", path = "/cartoes/clientes")
public interface CartaoClienteResourceClient {

    @GetMapping(params = "bi")
    List<CartaoCliente> getCartaoClienteByBi(@RequestParam("bi") String bi);

    @GetMapping(params = "renda")
    public List<Cartao> getCartoesRendaMenorOrIgual(@RequestParam("renda") Long renda);

}
