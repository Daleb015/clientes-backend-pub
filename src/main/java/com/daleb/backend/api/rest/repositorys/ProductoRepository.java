package com.daleb.backend.api.rest.repositorys;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.daleb.backend.api.rest.models.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
	public List<Producto> findByNombreContainingIgnoreCase(String term);
}
