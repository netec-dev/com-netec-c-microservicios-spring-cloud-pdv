package com.netec.c.microservicios.spring.cloud.pdv.cliente.controller;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.startsWith;

import java.util.concurrent.TimeUnit;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.netec.c.microservicios.spring.cloud.pdv.cliente.model.Cliente;
import io.specto.hoverfly.junit.rule.HoverflyRule;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ClienteControllerTest {

	private static Logger LOGGER = LoggerFactory.getLogger(ClienteControllerTest.class);

	@Autowired
	TestRestTemplate template;

	@ClassRule
	public static HoverflyRule hoverflyRule = HoverflyRule
			.inSimulationMode(dsl(
					service("servicio-de-cuentas:9021")
							.andDelay(200, TimeUnit.MILLISECONDS).forAll()
							.get(startsWith("/cliente/"))
							.willReturn(success("[{\"id\":\"1\",\"numeroCuenta\":\"1234567890\",\"balance\":5000}]",
									"application/json")),
					service("servicio-de-cuentas:9022")
							.andDelay(10000, TimeUnit.MILLISECONDS).forAll()
							.get(startsWith("/cliente/"))
							.willReturn(success("[{\"id\":\"3\",\"numeroCuenta\":\"1234567892\",\"balance\":10000}]",
									"application/json")),
					service("servicio-de-cuentas:9023")
							.andDelay(50, TimeUnit.MILLISECONDS).forAll()
							.get(startsWith("/cliente/"))
							.willReturn(success("[{\"id\":\"2\",\"numeroCuenta\":\"1234567891\",\"balance\":8000}]",
									"application/json"))))
			.printSimulationData();

	@Test
	public void testClientesConCuentas() {
		int a = 0, b = 0, d = 0;
		for (int i = 0; i < 1000; i++) {
			try {
				Cliente c = template.getForObject("/conCuentas/{id}", Cliente.class, 1);
				LOGGER.info("Cliente: {}", c);
				if (c != null) {
					if (c.getCuentas().get(0).getId().equals(1L))
						a++;
					else
						b++;
				}
			} catch (Exception e) {
				LOGGER.error("Error connecting with service", e);
				d++;
			}
		}
		LOGGER.info("RESULTADO: 9020={}, 9021={}, 9022={}", a, b, d);
	}

}