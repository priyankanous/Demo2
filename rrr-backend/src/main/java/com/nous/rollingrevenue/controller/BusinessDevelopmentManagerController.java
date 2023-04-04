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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.BusinessDevelopmentManagerService;
import com.nous.rollingrevenue.vo.BDMVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdm")
@CrossOrigin(origins = "*")
public class BusinessDevelopmentManagerController {

	@Autowired
	BusinessDevelopmentManagerService businessDevelopmentManagerService;

	@Operation(summary = "save BDM")
	@PostMapping
	public WSResponse<BDMVO> saveBDM(@RequestBody @Valid BDMVO bdmVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, businessDevelopmentManagerService.addBDMDetails(bdmVO));
	}

	@Operation(summary = "Update BDM")
	@PutMapping(path = "{bdmId}")
	public WSResponse<BDMVO> updateBDM(@PathVariable @Valid Long bdmId, @RequestBody @Valid BDMVO bdmVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				businessDevelopmentManagerService.updateBDMDetails(bdmId, bdmVO));
	}

	@Operation(summary = "Get BDM by Id")
	@GetMapping(path = "{bdmId}")
	public WSResponse<BDMVO> getBdmById(@PathVariable @Valid Long bdmId) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, businessDevelopmentManagerService.getBdmById(bdmId));
	}

	@Operation(summary = "Delete BDM by Id")
	@DeleteMapping(path = "{bdmId}")
	public WSResponse<String> removeBDM(@PathVariable @Valid Long bdmId) {
		businessDevelopmentManagerService.deleteBDM(bdmId);
		String data = "Business Development Manager id  " + bdmId + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get All BDM")
	@GetMapping
	public WSResponse<List<BDMVO>> getBDMDetails() {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, businessDevelopmentManagerService.getBDM());
	}
	
	@Operation(summary = "Get BusinessDevelopmentManager By Pagination")
	@GetMapping("/page")
	public WSResponse<List<BDMVO>> getBusinessDevelopmentManagerPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "bdmId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				businessDevelopmentManagerService.getPagination(pagenumber, pagesize, sortBy));
	}

}
