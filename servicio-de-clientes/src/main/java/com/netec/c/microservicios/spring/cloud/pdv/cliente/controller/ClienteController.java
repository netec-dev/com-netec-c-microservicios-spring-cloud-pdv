package com.netec.c.microservicios.spring.cloud.pdv.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netec.c.microservicios.spring.cloud.pdv.cliente.CuentaCliente;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cliente;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cuenta;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	CuentaCliente accountClient;

	@Autowired
	ClienteRepository clienteRepo;

	@PostMapping
	public Cliente altaCliente(@RequestBody Cliente cliente) {
		return clienteRepo.add(cliente);
	}

	@PutMapping
	public Cliente actualizaCliente(@RequestBody Cliente cliente) {
		return clienteRepo.update(cliente);
	}

	@GetMapping("/{idCliente}")
	public Cliente obtenClientePorId(@PathVariable("idCliente") Long idCliente) {
		return clienteRepo.findById(idCliente);
	}

	@GetMapping("/conCuentas/{idCliente}")
	public Cliente obtenClientePorIdConCuentas(@PathVariable("idCliente") Long idCliente) {
		System.out.println("-----------------------------------");
		List<Cuenta> accounts = accountClient.findByCustomer(idCliente);
		Cliente c = clienteRepo.findById(idCliente);
		c.setCuentas(accounts);
		return c;
	}

	@PostMapping("/ids")
	public List<Cliente> obtenClientes(@RequestBody List<Long> ids) {
		return clienteRepo.find(ids);
	}

	@DeleteMapping("/{id}")
	public void eliminaCliente(@PathVariable("id") Long id) {
		clienteRepo.delete(id);
	}

}
