package com.daleb.backend.api.rest.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.daleb.backend.api.rest.models.Factura;

@Repository
public interface FacturaRepository extends MongoRepository<Factura, String> {

}
