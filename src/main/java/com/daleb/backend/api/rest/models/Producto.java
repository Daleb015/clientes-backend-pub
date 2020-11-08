package com.daleb.backend.api.rest.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "productos")
public class Producto {
	@Id
	private String id;
	private String nombre;
	private Double precio;
	@Field("create_at")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
}
