package com.daleb.backend.api.rest.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document(collection = "Cliente")
public class Cliente {

	@Id
	private String id;
	private String nombre;
	private String apellido;

	private String email;

	@Field("create_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

}
