package com.daleb.backend.api.rest.services;

import java.util.List;

import com.daleb.backend.api.rest.models.Region;

public interface RegionService {
	
	List<Region> getAllRegions();
	
	Region save(Region region);
	
	Region findById(String id);
	
	Region update(Region region);
}
