package com.netec.c.microservicios.spring.cloud.pdv.gateway.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netec.c.microservicios.spring.cloud.pdv.gateway.fallback.response.AccountFallbackResponse;

public class AccountFallbackProvider implements FallbackProvider {

	@Override
	public String getRoute() {
		return "servicio-de-cuentas";
	}

//	@Override
	public ClientHttpResponse fallbackResponse(Throwable cause) {
		return new ClientHttpResponse() {

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				AccountFallbackResponse response = new AccountFallbackResponse("1.2", cause.getMessage());
				return new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(response));
			}

			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}

			@Override
			public void close() {

			}

		};
	}

//	@Override
	public ClientHttpResponse fallbackResponse2() {
		return new ClientHttpResponse() {

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("{\"status\":\"200\"}".getBytes());
			}

			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}

			@Override
			public void close() {

			}

		};
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("{\"status\":\"200\"}".getBytes());
			}

			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}

			@Override
			public void close() {

			}

		};
	}

}
