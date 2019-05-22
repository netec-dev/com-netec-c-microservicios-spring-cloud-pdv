package com.netec.c.microservicios.spring.cloud.pdv.cliente;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cliente;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.TipoDeCliente;
import com.netec.c.microservicios.spring.cloud.pdv.cliente.repository.ClienteRepository;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ClienteApp {

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
