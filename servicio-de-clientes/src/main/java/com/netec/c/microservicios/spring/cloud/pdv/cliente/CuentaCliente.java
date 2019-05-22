package com.netec.c.microservicios.spring.cloud.pdv.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cuenta;


@FeignClient(name = "servicio-de-cuentas")
public interface CuentaCliente {

	@GetMapping("/cliente/{idCliente}")
	List<Cuenta> findByCustomer(@PathVariable("idCliente") Long idCliente);
	
}
