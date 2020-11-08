package com.daleb.backend.api.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "facturas_items")
public class ItemFactura {
	@Id
	private String id;
	private Integer cantidad;

	public Double calcularImporte() {
		return cantidad.doubleValue();
	}

}
