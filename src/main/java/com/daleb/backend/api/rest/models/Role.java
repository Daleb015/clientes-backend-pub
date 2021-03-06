package com.daleb.backend.api.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Role")
public class Role {
	@Id
	private String id;

	private String nombre;
}
