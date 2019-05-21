package com.netec.c.microservicios.spring.cloud.pdv.cliente.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cliente;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cuenta;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	RestTemplate template;
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

	@GetMapping("/{id}")
	public Cliente obtenClientePorId(@PathVariable("id") Long id) {
		return clienteRepo.findById(id);
	}

	@GetMapping("/conCuentas/{id}")
	public Cliente obtenClientePorIdConCuentas(@PathVariable("id") Long id) {
		Cuenta[] accounts = template.getForObject("http://servicio-de-cuentas/cliente/{idCliente}", Cuenta[].class, id);
		System.out.println("-------cuentas:" + accounts);
		Cliente c = clienteRepo.findById(id);
		System.out.println("-------cliente:" + c);
		c.setCuentas(Arrays.stream(accounts).collect(Collectors.toList()));
		System.out.println("-------cliente:" + c);
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
