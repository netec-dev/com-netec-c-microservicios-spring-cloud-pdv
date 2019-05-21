package com.netec.c.microservicios.spring.cloud.pdv.pedido.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.Pedido;

public class PedidoRepository {

	private List<Pedido> orders = new ArrayList<>();

	public Pedido add(Pedido order) {
		order.setId((long) (orders.size() + 1));
		orders.add(order);
		return order;
	}

	public Pedido update(Pedido order) {
		orders.set(order.getId().intValue() - 1, order);
		return order;
	}

	public Pedido findById(Long id) {
		Optional<Pedido> order = orders.stream().filter(p -> p.getId().equals(id)).findFirst();
		if (order.isPresent())
			return order.get();
		else
			return null;
	}

	public void delete(Long id) {
		orders.remove(id.intValue());
	}

	public List<Pedido> find(List<Long> ids) {
		return orders.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
	}

	public int countByCustomerId(Long idCliente) {
		return (int) orders.stream().filter(p -> p.getIdCliente().equals(idCliente)).count();
	}
}