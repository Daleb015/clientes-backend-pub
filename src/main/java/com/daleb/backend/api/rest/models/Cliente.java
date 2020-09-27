package com.daleb.backend.api.rest.models;

import java.time.ZonedDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "Cliente")
public class Cliente {

	@Id
	private String id;
	@NotEmpty(message = "El campo nombre no puede estar vacio")
	@Size(min = 4, max = 12, message = "El nombre debe tener de 4 a 12 caracteres")
	private String nombre;
	@NotEmpty
	@NotNull
	private String apellido;
	@NotEmpty
	@Email(message = "El correo no tiene un formato valido")
	private String email;

	@Field("create_at")
	// @JsonFormat(pattern = "yyyy-MM-dd")
	private ZonedDateTime createAt;

}
