package com.daleb.backend.api.rest.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@DBRef
	private Cliente cliente;
	
	private List<ItemFactura> items;
}
