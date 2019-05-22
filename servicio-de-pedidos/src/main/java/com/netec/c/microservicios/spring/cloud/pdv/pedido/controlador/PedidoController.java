package com.netec.c.microservicios.spring.cloud.pdv.pedido.controlador;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.client.ClienteCliente;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.client.CuentaCliente;
import com.netec.c.microservicios.spring.cloud.pdv.pedido.client.ProductoCliente;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	CuentaCliente cuentaCliente;
	@Autowired
	ClienteCliente clienteCliente;
	@Autowired
	ProductoCliente productoCliente;

	@PostMapping
	public Pedido preparar(@RequestBody Pedido pedido) throws JsonProcessingException  {
		int precio = 0;
		List<Producto> productos = productoCliente.findByIds(pedido.getIdsProductos());
		LOGGER.info("Products found: {}", mapper.writeValueAsString(productos));
		Cliente cliente = clienteCliente.findByIdWithAccounts(pedido.getIdCliente());
		LOGGER.info("Customer found: {}", mapper.writeValueAsString(cliente));
		for (Producto producto : productos)
			precio += producto.getPrecio();
		final int precioConDescuento = obtenPrecioConDescuento(precio, cliente);
		LOGGER.info("Discounted price: {}",
				mapper.writeValueAsString(Collections.singletonMap("precio", precioConDescuento)));
		Optional<Cuenta> cuenta = cliente.getCuentas().stream().filter(a -> (a.getBalance() > precioConDescuento))
				.findFirst();
		if (cuenta.isPresent()) {
			pedido.setIdCuenta(cuenta.get().getId());
			pedido.setEstatusPedido(EstatusDePedido.ACEPTADO);
			pedido.setPrecio(precioConDescuento);
			LOGGER.info("Account found: {}", mapper.writeValueAsString(cuenta.get()));
		} else {
			pedido.setEstatusPedido(EstatusDePedido.RECHAZADO);
			LOGGER.info("Account not found: {}", mapper.writeValueAsString(cliente.getCuentas()));
		}
		Map<String, String> m = MDC.getCopyOfContextMap();
		return pedidoRepo.add(pedido);
	}

	@PutMapping("/{id}")
	public Pedido aceptar(@PathVariable Long id) throws JsonProcessingException  {
		final Pedido pedido = pedidoRepo.findById(id);
		LOGGER.info("Order found: {}", mapper.writeValueAsString(pedido));
		cuentaCliente.withdraw(pedido.getIdCuenta(), pedido.getPrecio());

		HashMap<String, Object> log = new HashMap<>();
		log.put("accountId", pedido.getIdCuenta());
		log.put("price", pedido.getPrecio());
		LOGGER.info("Account modified: {}", mapper.writeValueAsString(log));

		pedido.setEstatusPedido(EstatusDePedido.HECHO);
		LOGGER.info("Order status changed: {}",
				mapper.writeValueAsString(Collections.singletonMap("status", pedido.getEstatusPedido())));
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
