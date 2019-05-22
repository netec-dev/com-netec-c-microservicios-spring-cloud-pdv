package com.netec.c.microservicios.spring.cloud.pdv.cuenta.model;

public class Cuenta {

	private Long id;
	private String numeroCuenta;
	private int balance;
	private Long idCliente;

	public Cuenta() {

	}

	public Cuenta(String numeroCuenta, int balance, Long idCliente) {
		this.numeroCuenta = numeroCuenta;
		this.balance = balance;
		this.idCliente = idCliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

}
