package com.netec.c.microservicios.spring.cloud.pdv.producto.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.netec.c.microservicios.spring.cloud.pdv.producto.model.Producto;

public class ProductoRepository {

	private List<Producto> productos = new ArrayList<>();
	
	public Producto add(Producto producto) {
		producto.setId((long) (productos.size()+1));
		productos.add(producto);
		return producto;
	}
	
	public Producto update(Producto producto) {
		productos.set(producto.getId().intValue() - 1, producto);
		return producto;
	}
	
	public Producto findById(Long id) {
		Optional<Producto> producto = productos.stream().filter(p -> p.getId().equals(id)).findFirst();
		if (producto.isPresent())
			return producto.get();
		else
			return null;
	}
	
	public void delete(Long id) {
		productos.remove(id.intValue());
	}
	
	public List<Producto> find(List<Long> ids) {
		return productos.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
	}
}
