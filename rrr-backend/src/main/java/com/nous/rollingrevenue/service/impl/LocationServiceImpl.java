package com.nous.rollingrevenue.service.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Location;
import com.nous.rollingrevenue.repository.LocationRepository;
import com.nous.rollingrevenue.service.LocationService;
import com.nous.rollingrevenue.vo.LocationVO;


@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository locationRepository;

	@Override
	public Location addLocation(Location location) {
		return locationRepository.save(location);
	}

	@Override
	@CachePut(value = "location", key = "#locationId")
	public Location updateLocation(Long locationId, LocationVO locationVO) {
		Location location = locationRepository.findById(locationId)
				.orElseThrow(() -> new RecordNotFoundException("Location not found for id:" + locationId));
		location.setLocationName(locationVO.getLocationName());
		location.setLocationDisplayName(locationVO.getLocationDisplayName());
		return locationRepository.save(location);
	}

	@Override
	@Cacheable(value = "location", key = "#locationId")
	public Location getLocation(Long locationId) {
		Optional<Location> locationOptional = locationRepository.findById(locationId);
		if (locationOptional.isPresent()) {
			return locationOptional.get();
		}
		throw new RecordNotFoundException("location not found for id:" + locationId);
	}

	@Override
	@Transactional
	@CacheEvict(value = "location", key = "#locationId") 
	public void deleteLocation(Long locationId) {
		Optional<Location> locationOptional = locationRepository.findById(locationId);
		if (locationOptional.isPresent()) {
			locationRepository.deleteById(locationId);
		} else {
			throw new RecordNotFoundException("Location not found for id:" + locationId);
		}
	}

	@Override
	@Transactional
	public List<Location> getLocations() {
		return locationRepository.findAll();
	}
}
