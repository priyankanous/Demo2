package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.LocationConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Location;
import com.nous.rollingrevenue.repository.LocationRepository;
import com.nous.rollingrevenue.service.LocationService;
import com.nous.rollingrevenue.vo.LocationVO;

@Service
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository locationRepository;

	@Override
	@Transactional
	public LocationVO addLocation(LocationVO locationVO) {
		Location location = LocationConverter.convertLocationVOToLocation(locationVO);
		return LocationConverter.convertLocationToLocationVO(locationRepository.save(location));
	}

	@Override
	@Transactional
	@CachePut(value = "location", key = "#locationId")
	public LocationVO updateLocation(Long locationId, LocationVO locationVO) {
		Location location = locationRepository.findById(locationId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + locationId));
		location.setLocationName(locationVO.getLocationName());
		location.setLocationDisplayName(locationVO.getLocationDisplayName());
		return LocationConverter.convertLocationToLocationVO(locationRepository.save(location));
	}

	@Override
	@Cacheable(value = "location", key = "#locationId")
	public LocationVO getLocation(Long locationId) {
		Optional<Location> locationOptional = locationRepository.findById(locationId);
		if (locationOptional.isPresent()) {
			return LocationConverter.convertLocationToLocationVO(locationOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + locationId);
	}

	@Override
	@Transactional
	@CacheEvict(value = "location", key = "#locationId")
	public void deleteLocation(Long locationId) {
		Optional<Location> locationOptional = locationRepository.findById(locationId);
		if (locationOptional.isPresent()) {
			locationRepository.deleteById(locationId);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + locationId);
		}
	}

	@Override
	public List<LocationVO> getLocations() {
		List<LocationVO> locationVOs = new ArrayList<>();
		locationRepository.findAll().stream().forEach(location -> {
			locationVOs.add(LocationConverter.convertLocationToLocationVO(location));
		});
		return locationVOs;
	}
	
	@Override
	public List<LocationVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<LocationVO> locationVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<Location> pageResult = locationRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				locationVOs.add(LocationConverter.convertLocationToLocationVO(e));
			});
			return locationVOs;
		}
		return Collections.emptyList();
	}
}
