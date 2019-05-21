package com.netec.c.microservicios.spring.cloud.pdv.cliente;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cliente;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.TipoDeCliente;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.repository.ClienteRepository;

@SpringBootApplication
@RibbonClient(name = "servicio-de-cuentas")
public class ClienteApp {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ClienteApp.class).run(args);
	}

	@Bean
	ClienteRepository repository() {
		ClienteRepository repository = new ClienteRepository();
		repository.add(new Cliente("John Scott", TipoDeCliente.NUEVO));
		repository.add(new Cliente("Adam Smith", TipoDeCliente.REGULAR));
		repository.add(new Cliente("Jacob Ryan", TipoDeCliente.VIP));
		return repository;
	}
	
}
