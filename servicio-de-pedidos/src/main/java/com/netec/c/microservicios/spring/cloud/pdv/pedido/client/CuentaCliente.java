package com.netec.c.microservicios.spring.cloud.pdv.pedido.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.netec.c.microservicios.spring.cloud.pdv.cuenta.contrato.ServicioCuenta;

@FeignClient(name = "servicio-de-cuentas")
public interface CuentaCliente extends ServicioCuenta {
}