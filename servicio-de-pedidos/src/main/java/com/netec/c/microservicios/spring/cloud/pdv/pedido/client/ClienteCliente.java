package com.netec.c.microservicios.spring.cloud.pdv.pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.Cliente;

@FeignClient(name = "servicio-de-clientes")
public interface ClienteCliente {

	@GetMapping("/conCuentas/{idCliente}")
	Cliente findByIdWithAccounts(@PathVariable("idCliente") Long customerId);
	
}
