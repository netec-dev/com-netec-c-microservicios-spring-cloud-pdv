package com.netec.c.microservicios.spring.cloud.pdv.cuenta.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netec.c.microservicios.spring.cloud.pdv.cuenta.model.Cuenta;
import com.netec.c.microservicios.spring.cloud.pdv.cuenta.repository.CuentaRepository;

@RestController
public class CuentaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaController.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
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
	public Cuenta retiro(@PathVariable("id") Long id, @PathVariable("cantidad") int cantidad) throws JsonProcessingException {
		Cuenta cuenta = cuentaRepo.findById(id);
		LOGGER.info("Account found: {}", mapper.writeValueAsString(cuenta));
		cuenta.setBalance(cuenta.getBalance() - cantidad);
		LOGGER.info("Current balance: {}", mapper.writeValueAsString(Collections.singletonMap("balance", cuenta.getBalance())));
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
