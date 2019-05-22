package com.netec.c.microservicios.spring.cloud.pdv.producto;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.netec.c.microservicios.spring.cloud.pdv.producto.model.Producto;
import com.netec.c.microservicios.spring.cloud.pdv.producto.repository.ProductoRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductoApp {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ProductoApp.class).run(args);
	}

	@Bean
	ProductoRepository repository() {
		ProductoRepository productoRepo = new ProductoRepository();
		productoRepo.add(new Producto("Producto 1", 100));
		productoRepo.add(new Producto("Producto 2", 200));
		productoRepo.add(new Producto("Producto 3", 300));
		productoRepo.add(new Producto("Producto 4", 400));
		productoRepo.add(new Producto("Producto 5", 500));
		productoRepo.add(new Producto("Producto 6", 600));
		productoRepo.add(new Producto("Producto 7", 700));
		productoRepo.add(new Producto("Producto 8", 800));
		productoRepo.add(new Producto("Producto 9", 900));
		productoRepo.add(new Producto("Producto 10", 1000));
		return productoRepo;
	}
	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(true);
	    loggingFilter.setMaxPayloadLength(1000);
	    loggingFilter.setAfterMessagePrefix("REQ:");
	    return loggingFilter;
	}
	
//	@Bean
//	public Sampler defaultSampler() {
//		return new AlwaysSampler();
//	}

}
