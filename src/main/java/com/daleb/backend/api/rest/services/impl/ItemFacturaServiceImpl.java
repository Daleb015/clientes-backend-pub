package com.daleb.backend.api.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daleb.backend.api.rest.models.ItemFactura;
import com.daleb.backend.api.rest.repositorys.ItemFacturaRepository;
import com.daleb.backend.api.rest.services.ItemFacturaService;

@Service
public class ItemFacturaServiceImpl implements ItemFacturaService {

	@Autowired
	private ItemFacturaRepository itemFacturaRepository;

	@Override
	public void delete(List<ItemFactura> items) {
		items.stream()
				.forEach(x -> itemFacturaRepository.deleteById(x.getId()));
	}

}
