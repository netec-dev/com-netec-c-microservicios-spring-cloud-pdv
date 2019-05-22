package com.netec.c.microservicios.spring.cloud.pdv.pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.Cuenta;

@FeignClient(name = "servicio-de-cuentas")
public interface CuentaCliente {

	@PutMapping("/retiro/{idCuenta}/{cantidad}")
	Cuenta withdraw(@PathVariable("idCuenta") Long id, @PathVariable("cantidad") int amount);

}
