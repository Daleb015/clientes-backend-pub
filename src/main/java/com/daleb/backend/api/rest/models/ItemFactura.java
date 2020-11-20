package com.daleb.backend.api.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class ItemFactura {

	@Id
	private String id;

	private Integer cantidad;

	@DBRef
	private Producto producto;

	public Double getImporte() {
		return cantidad.doubleValue() * producto.getPrecio();
	}

}
