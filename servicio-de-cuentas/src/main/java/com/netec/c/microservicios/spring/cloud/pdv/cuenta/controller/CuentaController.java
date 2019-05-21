package com.netec.c.microservicios.spring.cloud.pdv.cuenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netec.c.microservicios.spring.cloud.pdv.cuenta.model.Cuenta;
import com.netec.c.microservicios.spring.cloud.pdv.cuenta.repository.CuentaRepository;

@RestController
public class CuentaController {

	@Autowired
	CuentaRepository cuentaRepo;

	@PostMapping
	public Cuenta altaCuenta(@RequestBody Cuenta cuenta) {
		return cuentaRepo.add(cuenta);
	}

	@PutMapping
	public Cuenta actualizaCuenta(@RequestBody Cuenta cuenta) {
		return cuentaRepo.update(cuenta);
	}

	@PutMapping("/retiro/{id}/{cantidad}")
	public Cuenta retiro(@PathVariable("id") Long id, @PathVariable("cantidad") int cantidad) {
		Cuenta cuenta = cuentaRepo.findById(id);
		cuenta.setBalance(cuenta.getBalance() - cantidad);
		return cuentaRepo.update(cuenta);
	}

	@GetMapping("/{id}")
	public Cuenta obtenCuentaPorId(@PathVariable("id") Long id) {
		return cuentaRepo.findById(id);
	}

	@GetMapping("/cliente/{idCliente}")
	public List<Cuenta> obtenCuentasPorIdCliente(@PathVariable("idCliente") Long idCliente) {
		return cuentaRepo.findByCustomer(idCliente);
	}

	@PostMapping("/ids")
	public List<Cuenta> obtenCuentas(@RequestBody List<Long> ids) {
		return cuentaRepo.find(ids);
	}

	@DeleteMapping("/{id}")
	public void eliminaCuenta(@PathVariable("id") Long id) {
		cuentaRepo.delete(id);
	}

}
