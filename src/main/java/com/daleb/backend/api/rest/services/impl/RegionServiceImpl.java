package com.daleb.backend.api.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daleb.backend.api.rest.models.Region;
import com.daleb.backend.api.rest.repositorys.RegionRepository;
import com.daleb.backend.api.rest.services.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	RegionRepository regionRepository;
	
	@Override
	public List<Region> getAllRegions() {
		
		return regionRepository.findAll();
	}

	@Override
	public Region save(Region region) {
		
		return regionRepository.insert(region);
	}

	@Override
	public Region findById(String id) {
		
		return regionRepository.findById(id).get();
	}

	@Override
	public Region update(Region region) {
		
		return regionRepository.save(region);
	}

}
