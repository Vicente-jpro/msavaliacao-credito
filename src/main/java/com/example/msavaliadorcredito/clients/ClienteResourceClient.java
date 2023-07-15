package com.example.msavaliadorcredito.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.msavaliadorcredito.models.msclientes.DadosCliente;

@FeignClient(url = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @PostMapping
    ResponseEntity salvar(@RequestBody DadosCliente cliente);

    @PatchMapping("/{id_cliente}")
    public ResponseEntity atualizar(@RequestBody Cliente cliente,
            @PathVariable("id_cliente") Integer IdCliente);

    @GetMapping
    public List<Cliente> listarClientes();

    @GetMapping("/{id_cliente}")
	public Cliente getCliente(@PathVariable("id_cliente") Integer IdCliente) {

		return this.clienteService.getCliente(IdCliente);
	}

    @DeleteMapping("/{id_cliente}")
    public void eliminar(@PathVariable("id_cliente") Integer IdCliente);

}
