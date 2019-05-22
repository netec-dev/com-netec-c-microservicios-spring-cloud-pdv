package com.netec.c.microservicios.spring.cloud.pdv.producto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netec.c.microservicios.spring.cloud.pdv.producto.model.Producto;
import com.netec.c.microservicios.spring.cloud.pdv.producto.repository.ProductoRepository;

@RestController
public class ProductoController {

	@Autowired
	ProductoRepository repository;

	@PostMapping
	public Producto altaProducto(@RequestBody Producto product) {
		return repository.add(product);
	}

	@PutMapping
	public Producto actualizaProducto(@RequestBody Producto product) {
		return repository.update(product);
	}

	@GetMapping("/{id}")
	public Producto obtenProductoPorId(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

	@PostMapping("/ids")
	public List<Producto> obtenProductosPorIds(@RequestBody List<Long> ids) {
		List<Producto> productos = repository.find(ids);
		return productos;
	}

	@DeleteMapping("/{id}")
	public void eliminaProducto(@PathVariable("id") Long id) {
		repository.delete(id);
	}

}
