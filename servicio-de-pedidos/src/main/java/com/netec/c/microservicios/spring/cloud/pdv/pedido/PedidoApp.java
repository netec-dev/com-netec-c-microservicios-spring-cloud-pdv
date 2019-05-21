package com.netec.c.microservicios.spring.cloud.pdv.pedido;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netec.c.microservicios.spring.cloud.pdv.pedido.repo.PedidoRepository;



@SpringBootApplication
//@RibbonClients({
//	@RibbonClient(name = "servicio-de-cuentas"),
//	@RibbonClient(name = "servicio-de-clientes"),
//	@RibbonClient(name = "servicio-de-productos")
//})
@EnableDiscoveryClient
public class PedidoApp {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(PedidoApp.class).run(args);
	}
	
	@Bean
	PedidoRepository repository() {
		return new PedidoRepository();
	}

}