package com.daleb.backend.api.rest.services;

import com.daleb.backend.api.rest.models.Factura;

public interface FacturaService {
	public Factura findById(String id);
	public Factura save(Factura factura);
	public void delete(String id);
}
