package com.netec.c.microservicios.spring.cloud.pdv.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DescubrimientoApp {

	public static void main(String[] args) {

		new SpringApplicationBuilder(DescubrimientoApp.class).run(args);

	}

}