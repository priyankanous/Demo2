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
import com.nous.rollingrevenue.service.RegionService;
import com.nous.rollingrevenue.vo.RegionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/regions")
@CrossOrigin(origins = "*")
public class RegionController {

	@Autowired
	private RegionService regionService;

	@Operation(summary = "Get All Regions")
	@GetMapping
	public WSResponse<List<RegionVO>> getAllRegion() {
		List<RegionVO> regions = regionService.getAllRegions();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, regions);
	}

	@Operation(summary = "Save Region")
	@PostMapping
	public WSResponse<RegionVO> saveRegion(@RequestBody @Valid RegionVO regionVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, regionService.saveRegion(regionVO));
	}

	@Operation(summary = "Get Region by Id")
	@GetMapping(path = "{regionId}")
	public WSResponse<RegionVO> getRegionById(@PathVariable Long regionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, regionService.getRegionById(regionId));
	}

	@Operation(summary = "Update Region by Id")
	@PutMapping(path = "{regionId}")
	public WSResponse<RegionVO> updateRegion(@PathVariable Long regionId, @RequestBody @Valid RegionVO regionVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				regionService.updateRegion(regionId, regionVO));
	}

	@Operation(summary = "Delete Region by Id")
	@DeleteMapping(path = "{regionId}")
	public WSResponse<String> deleteRegion(@PathVariable Long regionId) {
		regionService.deleteRegionById(regionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Get Region By Pagination")
	@GetMapping("/page")
	public WSResponse<List<RegionVO>> getRegionByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "regionId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, regionService.getPagination(pagenumber, pagesize, sortBy));
	}

}
