package com.netec.c.microservicios.spring.cloud.pdv.gateway;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.netec.c.microservicios.spring.cloud.pdv.gateway.modelo.EstatusDePedido;
import com.netec.c.microservicios.spring.cloud.pdv.gateway.modelo.Pedido;

public class GatewayControllerTest {

	TestRestTemplate template = new TestRestTemplate();

	@Test
	public void testOrder() throws InterruptedException {
		for (int i = 0; i < 1; i++) {
			sendAndAcceptOrder();
//			Thread.sleep(500);
		}
	}

	private void sendAndAcceptOrder() {
//		try {
//			Random r = new Random();
//			Pedido pedido = new Pedido();
//			pedido.setIdCliente((long) r.nextInt(3) + 1);
//			pedido.setIdsProductos(Arrays.asList(new Long[] { (long) r.nextInt(10) + 1, (long) r.nextInt(10) + 1 }));
//			pedido = template.postForObject("http://localhost:8080/api/pedido", pedido, Pedido.class);
//			if (pedido.getEstatusPedido() != EstatusDePedido.RECHAZADO) {
//				template.put("http://localhost:8080/api/pedido/{id}", null, pedido.getId());
//			}
//		} catch (Exception e) {
//
//		}
	}
	
}
