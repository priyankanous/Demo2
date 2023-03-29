package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public WSResponse<LocationVO> saveLocation(@RequestBody @Valid LocationVO locationVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, locationService.addLocation(locationVO));
	}

	@Operation(summary = "Update Location by Id")
	@PutMapping(path = "{locationId}")
	public WSResponse<LocationVO> updateLocation(@PathVariable @Valid Long locationId,
			@RequestBody @Valid LocationVO locationVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, locationService.updateLocation(locationId, locationVO));
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

}
