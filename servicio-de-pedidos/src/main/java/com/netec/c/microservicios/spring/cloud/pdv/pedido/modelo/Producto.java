package com.netec.c.microservicios.spring.cloud.pdv.pedido.modelo;

public class Producto {

	private Long id;
	private String nombre;
	private int precio;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}



}
