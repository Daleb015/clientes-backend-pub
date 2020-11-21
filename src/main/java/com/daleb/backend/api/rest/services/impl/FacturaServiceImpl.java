package com.daleb.backend.api.rest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daleb.backend.api.rest.models.Factura;
import com.daleb.backend.api.rest.repositorys.FacturaRepository;
import com.daleb.backend.api.rest.services.FacturaService;
import com.daleb.backend.api.rest.services.ItemFacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ItemFacturaService itemFacturaService;

	@Override
	public Factura findById(String id) {
		return facturaRepository.findById(id).orElse(null);
	}

	@Override
	public Factura save(Factura factura) {

		if (factura.getId() != null) {
			return facturaRepository.save(factura);
		} else {
			return facturaRepository.insert(factura);
		}

	}

	@Override
	public void delete(String id) {

		Factura factura = facturaRepository.findById(id).orElse(null);

		if (factura != null && !factura.getItems().isEmpty()) {
			itemFacturaService.delete(factura.getItems());
		}

		facturaRepository.deleteById(id);
	}

}
