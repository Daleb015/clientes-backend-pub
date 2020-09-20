package com.daleb.backend.api.rest.models;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "Cliente")
public class Cliente {

	@Id
	private String id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	@NotNull
	private String apellido;
	@NotEmpty
	@Email
	private String email;

	@Field("create_at")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

}
