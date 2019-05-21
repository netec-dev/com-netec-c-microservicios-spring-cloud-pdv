package com.netec.c.microservicios.spring.cloud.pdv.cliente.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cliente;

public class ClienteRepository {

	private List<Cliente> customers = new ArrayList<>();
	
	public Cliente add(Cliente customer) {
		customer.setId((long) (customers.size()+1));
		customers.add(customer);
		return customer;
	}
	
	public Cliente update(Cliente customer) {
		customers.set(customer.getId().intValue() - 1, customer);
		return customer;
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> customer = customers.stream().filter(p -> p.getId().equals(id)).findFirst();
		if (customer.isPresent())
			return customer.get();
		else
			return null;
	}
	
	public void delete(Long id) {
		customers.remove(id.intValue());
	}
	
	public List<Cliente> find(List<Long> ids) {
		return customers.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
	}
	
}
