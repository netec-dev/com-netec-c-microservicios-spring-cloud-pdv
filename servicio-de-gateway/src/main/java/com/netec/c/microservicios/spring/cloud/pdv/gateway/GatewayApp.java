package com.netec.c.microservicios.spring.cloud.pdv.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.netec.c.microservicios.spring.cloud.pdv.gateway.fallback.AccountFallbackProvider;
import com.netec.c.microservicios.spring.cloud.pdv.gateway.filter.AddResponseIDHeaderFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayApp {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(GatewayApp.class).run(args);
	}

	@Bean
	AddResponseIDHeaderFilter filter() {
		return new AddResponseIDHeaderFilter();
	}
	
	@Bean
	AccountFallbackProvider fallback() {
		return new AccountFallbackProvider();
	}
	
//	@Bean
//	public Sampler defaultSampler() {
//		return new AlwaysSampler();
//	}
	
}
