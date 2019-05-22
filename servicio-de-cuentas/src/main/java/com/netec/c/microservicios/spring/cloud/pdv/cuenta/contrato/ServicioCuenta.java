package com.netec.c.microservicios.spring.cloud.pdv.cuenta.contrato;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.netec.c.microservicios.spring.cloud.pdv.cuenta.model.Cuenta;

public interface ServicioCuenta {

	@PostMapping
	Cuenta alta(@RequestBody Cuenta cuenta);

	@PutMapping
	Cuenta actualiza(@RequestBody Cuenta cuenta);

	@PutMapping("/retiro/{idCuenta}/{cantidad}")
	Cuenta retiro(@PathVariable("idCuenta") Long id, @PathVariable("cantidad") int cantidad);

	@GetMapping("/{idCuenta}")
	Cuenta obtenPorId(@PathVariable("idCuenta") Long id);

	@GetMapping("/cliente/{idCliente}")
	List<Cuenta> obtenPorIdCliente(@PathVariable("idCliente") Long idCliente);

	@PostMapping("/idsCuentas")
	List<Cuenta> obtenPorIds(@RequestBody List<Long> idsCuentas);

	@DeleteMapping("/{idCuenta}")
	void baja(@PathVariable("idCuenta") Long idCuenta);

}