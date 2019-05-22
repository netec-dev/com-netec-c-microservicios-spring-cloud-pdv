package com.netec.c.microservicios.spring.cloud.pdv.pedido.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cliente;
import com.netec.c.microservicios.spring.cloud.pdv.cuenta.model.Cuenta;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.client.ClienteCliente;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.client.CuentaCliente;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.client.ProductoCliente;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.EstatusDePedido;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo.Pedido;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.repo.PedidoRepository;
import com.netec.c.microservicios.spring.cloud.pdv.producto.model.Producto;


@RestController
public class PedidoController {
	@Autowired
	PedidoRepository pedidoRepo;

	@Autowired
	CuentaCliente cuentaCliente;
	@Autowired
	ClienteCliente clienteCliente;
	@Autowired
	ProductoCliente productoCliente;

	@PostMapping
	public Pedido preparar(@RequestBody Pedido pedido) {
		int precio = 0;
		List<Producto> productos = productoCliente.findByIds(pedido.getIdsProductos());
		Cliente cliente = clienteCliente.findByIdWithAccounts(pedido.getIdCliente());
		for (Producto producto : productos)
			precio += producto.getPrecio();
		final int precioConDescuento = obtenPrecioConDescuento(precio, cliente);
		Optional<Cuenta> cuenta = cliente.getCuentas().stream().filter(a -> (a.getBalance() > precioConDescuento))
				.findFirst();
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
	public Pedido aceptar(@PathVariable Long id) throws JsonProcessingException {
		final Pedido pedido = pedidoRepo.findById(id);
		cuentaCliente.retiro(pedido.getIdCuenta(), pedido.getPrecio());
		HashMap<String, Object> log = new HashMap<>();
		log.put("accountId", pedido.getIdCuenta());
		log.put("price", pedido.getPrecio());
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
		descuento += (cantidadPedidos * 0.01);
		return (int) (precio - (precio * descuento));
	}

}
