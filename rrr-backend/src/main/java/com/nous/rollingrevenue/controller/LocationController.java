package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.LocationService;
import com.nous.rollingrevenue.vo.LocationVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/location")
@CrossOrigin(origins = "*")
public class LocationController {

	@Autowired
	LocationService locationService;

	@Operation(summary = "save location")
	@PostMapping
	public WSResponse<String> saveLocation(@RequestBody @Valid LocationVO locationVO) {
		locationService.addLocation(locationVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update Location by Id")
	@PutMapping(path = "{locationId}")
	public WSResponse<String> updateLocation(@PathVariable @Valid Long locationId,
			@RequestBody @Valid LocationVO locationVO) {
		locationService.updateLocation(locationId, locationVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get Location by Id")
	@GetMapping(path = "{locationId}")
	public WSResponse<LocationVO> getLocation(@PathVariable @Valid Long locationId) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, locationService.getLocation(locationId));
	}

	@Operation(summary = "Delete location by Id")
	@DeleteMapping(path = "{locationId}")
	public WSResponse<String> removeLocation(@PathVariable @Valid Long locationId) {
		locationService.deleteLocation(locationId);
		String data = "Location id  " + locationId + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get All Locations")
	@GetMapping
	public WSResponse<List<LocationVO>> getLocations() {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, locationService.getLocations());
	}

	@Operation(summary = "Get location By Pagination")
	@GetMapping("/page")
	public WSResponse<List<LocationVO>> getLocationByPagination(@RequestParam(defaultValue = "1") int pagenumber,
			@RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "locationId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				locationService.getPagination(pagenumber, pagesize, sortBy));
	}

	@Operation(summary = "Activate or Deactivate Location by Id")
	@PutMapping(path = "/activate-or-deactivate/{locationId}")
	public WSResponse<String> activateOrDeactivateLocationById(@PathVariable Long locationId) {
		locationService.activateOrDeactivateById(locationId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Get leaveLossFactor By LocationName")
	@GetMapping(path = "/{financialYear}/{locationName}")
	public WSResponse<Long> getLeaveLossFactorByLocationName(@PathVariable String financialYear,
			@PathVariable String locationName) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				locationService.getLeaveLossFactorByLocationName(financialYear, locationName));
	}
	
}
