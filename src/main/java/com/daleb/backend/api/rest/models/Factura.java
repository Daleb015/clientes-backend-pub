package com.daleb.backend.api.rest.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Document(collection = "facturas")
public class Factura {

	@Id
	private String id;
	private String descripcion;
	private String observacion;
	@Field("create_at")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@JsonIgnoreProperties({"facturas"})
	@DBRef
	private Cliente cliente;

	@DBRef
	private List<ItemFactura> items;

	public Double getTotal() {
		return this.items.stream().reduce(0.0,
				(result, itemFactura) -> result + itemFactura.getImporte(),
				Double::sum);
	}
}
