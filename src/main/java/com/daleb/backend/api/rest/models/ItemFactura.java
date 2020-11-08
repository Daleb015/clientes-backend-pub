package com.daleb.backend.api.rest.models;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class ItemFactura {

	private Integer cantidad;

	@DBRef
	private Producto producto;

	public Double getImporte() {
		return cantidad.doubleValue() * producto.getPrecio();
	}

}
