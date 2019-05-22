package com.netec.c.microservicios.spring.cloud.pdv.cuenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netec.c.microservicios.spring.cloud.pdv.cuenta.contrato.ServicioCuenta;
import com.netec.c.microservicios.spring.cloud.pdv.cuenta.model.Cuenta;
import com.netec.c.microservicios.spring.cloud.pdv.cuenta.repository.CuentaRepository;

@RestController
public class CuentaController implements ServicioCuenta {

	@Autowired
	CuentaRepository cuentaRepo;

	public Cuenta alta(@RequestBody Cuenta cuenta) {
		return cuentaRepo.add(cuenta);
	}

	public Cuenta actualiza(@RequestBody Cuenta cuenta) {
		return cuentaRepo.update(cuenta);
	}

	public Cuenta retiro(@PathVariable("id") Long id, @PathVariable("cantidad") int cantidad) {
		Cuenta cuenta = cuentaRepo.findById(id);
		cuenta.setBalance(cuenta.getBalance() - cantidad);
		return cuentaRepo.update(cuenta);
	}

	public Cuenta obtenPorId(@PathVariable("id") Long id) {
		return cuentaRepo.findById(id);
	}

	public List<Cuenta> obtenPorIdCliente(@PathVariable("idCliente") Long idCliente) {
		return cuentaRepo.findByCustomer(idCliente);
	}

	public List<Cuenta> obtenPorIds(@RequestBody List<Long> ids) {
		return cuentaRepo.find(ids);
	}

	public void baja(@PathVariable("id") Long id) {
		cuentaRepo.delete(id);
	}

}
