package com.daleb.backend.api.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Region")
public class Region {

	@Id
	private String id;

	private String nombre;

}
