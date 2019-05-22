package com.netec.c.microservicios.spring.cloud.pdv.pedido.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.netec.c.microservicios.spring.cloud.pdv.producto.model.Producto;

@FeignClient(name = "servicio-de-productos")
public interface ProductoCliente {

	@PostMapping("/ids")
	List<Producto> findByIds(List<Long> ids);
	
}
