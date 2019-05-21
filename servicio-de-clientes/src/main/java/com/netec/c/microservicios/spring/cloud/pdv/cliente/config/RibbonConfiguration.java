package com.netec.c.microservicios.spring.cloud.pdv.cliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

@Configuration
public class RibbonConfiguration {

	@Bean
	public IRule ribbonRule() {
		return new WeightedResponseTimeRule();
	}

}