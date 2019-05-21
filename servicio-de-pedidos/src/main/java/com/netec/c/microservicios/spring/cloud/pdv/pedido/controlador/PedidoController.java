package com.netec.c.microservicios.spring.cloud.pdv.pedido.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.Cliente;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.Cuenta;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.EstatusDePedido;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.Pedido;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.Producto;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.repo.PedidoRepository;

@RestController
public class PedidoController {	
	@Autowired
	PedidoRepository pedidoRepo;	
	@Autowired
	RestTemplate template;
	
	@PostMapping
	public Pedido preparar(@RequestBody Pedido pedido) {
		int precio = 0;
		Producto[] productos = template.postForObject("http://servicio-de-productos/ids", pedido.getIdsProductos(), Producto[].class);
		Cliente clientes = template.getForObject("http://servicio-de-clientes/conCuentas/{id}", Cliente.class, pedido.getIdCliente());
		for (Producto producto : productos) 
			precio += producto.getPrecio();
		final int precioConDescuento = obtenPrecioConDescuento(precio, clientes);
		Optional<Cuenta> cuenta = clientes.getCuentas().stream().filter(a -> (a.getBalance() > precioConDescuento)).findFirst();
		if (cuenta.isPresent()) {
			pedido.setIdCuenta(cuenta.get().getId());
			pedido.setEstatusPedido(EstatusDePedido.ACEPTADO);
			pedido.setPrecio(precioConDescuento);
		} else {
			pedido.setEstatusPedido(EstatusDePedido.RECHAZADO);
		}
		return pedidoRepo.add(pedido);
	}
	
	@PutMapping("/{id}")
	public Pedido aceptar(@PathVariable Long id) {
		final Pedido pedido = pedidoRepo.findById(id);
		template.put("http://servicio-de-clientes/retiro/{id}/{cantidad}", null, pedido.getIdCuenta(), pedido.getPrecio());
		pedido.setEstatusPedido(EstatusDePedido.HECHO);
		pedidoRepo.update(pedido);
		return pedido;
	}
	
	private int obtenPrecioConDescuento(int precio, Cliente cliente) {
		double descuento = 0;
		switch (cliente.getTipoCliente()) {
		case REGULAR:
			descuento += 0.05;
			break;
		case VIP:
			descuento += 0.1;
			break;
			
		default:
			break;
		}
		int cantidadPedidos = pedidoRepo.countByCustomerId(cliente.getId());
		descuento += (cantidadPedidos*0.01);
		return (int) (precio - (precio * descuento));
	}
	
}
