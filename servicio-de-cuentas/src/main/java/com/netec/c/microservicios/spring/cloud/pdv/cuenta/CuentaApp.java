package com.netec.c.microservicios.spring.cloud.pdv.cuenta;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.netec.c.microservicios.spring.cloud.pdv.cuenta.model.Cuenta;
import com.netec.c.microservicios.spring.cloud.pdv.cuenta.repository.CuentaRepository;



@SpringBootApplication
@EnableDiscoveryClient
public class CuentaApp {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(CuentaApp.class).run(args);
	}

	/**
	 * Creación de datos en memoria (cuentas)
	 * 
	 * @return
	 */
	@Bean
	CuentaRepository repository() {
		CuentaRepository repository = new CuentaRepository();
		repository.add(new Cuenta("1234567890", 5000, 1L));
		repository.add(new Cuenta("1234567891", 5000, 1L));
		repository.add(new Cuenta("1234567892", 0, 1L));
		repository.add(new Cuenta("1234567893", 5000, 2L));
		repository.add(new Cuenta("1234567894", 0, 2L));
		repository.add(new Cuenta("1234567895", 5000, 2L));
		repository.add(new Cuenta("1234567896", 0, 3L));
		repository.add(new Cuenta("1234567897", 5000, 3L));
		repository.add(new Cuenta("1234567898", 5000, 3L));
		return repository;
	}

//	@Bean
//	public Sampler defaultSampler() {
//		return new AlwaysSampler();
//	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeHeaders(true);
		loggingFilter.setMaxPayloadLength(1000);
		loggingFilter.setAfterMessagePrefix("REQ:");
		return loggingFilter;
	}
}
