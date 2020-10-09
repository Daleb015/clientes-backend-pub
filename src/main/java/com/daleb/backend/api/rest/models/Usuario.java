package com.daleb.backend.api.rest.models;

import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Usuario")
public class Usuario {
	@Id
	private String id;

	@Size(min = 20, max = 20, message = "El username debe tener longitud de 20 caracteres")
	private String username;

	@Size(min = 60, max = 60, message = "El password debe tener longitud de 60 caracteres")
	private String password;

	private Boolean enable;

	private String nombre;
	
	private String apellido;
	
	private String email;
	
	@DBRef
	private List<Role> roles;

}
