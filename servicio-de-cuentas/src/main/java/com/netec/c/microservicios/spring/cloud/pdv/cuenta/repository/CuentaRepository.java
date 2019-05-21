package com.netec.c.microservicios.spring.cloud.pdv.cuenta.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.netec.c.microservicios.spring.cloud.pdv.cuenta.model.Cuenta;

public class CuentaRepository {

	private List<Cuenta> cuentas = new ArrayList<>();
	
	public Cuenta add(Cuenta cuenta) {
		cuenta.setId((long) (cuentas.size()+1));
		cuentas.add(cuenta);
		return cuenta;
	}
	
	public Cuenta update(Cuenta cuenta) {
		cuentas.set(cuenta.getId().intValue() - 1, cuenta);
		return cuenta;
	}
	
	public Cuenta findById(Long id) {
		Optional<Cuenta> cuenta = cuentas.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (cuenta.isPresent())
			return cuenta.get();
		else
			return null;
	}
	
	public void delete(Long id) {
		cuentas.remove(id.intValue());
	}
	
	public List<Cuenta> find(List<Long> ids) {
		return cuentas.stream().filter(a -> ids.contains(a.getId())).collect(Collectors.toList());
	}
	
	public List<Cuenta> findByCustomer(Long idCliente) {
		return cuentas.stream().filter(a -> a.getIdCliente().equals(idCliente)).collect(Collectors.toList());
	}
	
}
