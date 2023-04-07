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
import com.nous.rollingrevenue.service.BusinessUnitService;
import com.nous.rollingrevenue.vo.BusinessUnitVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/business-unit")
@CrossOrigin(origins = "*")
public class BusinessUnitController {

	@Autowired
	BusinessUnitService businessUnitService;

	@Operation(summary = "save business unit")
	@PostMapping
	public WSResponse<BusinessUnitVO> saveBusinessUnit(@RequestBody @Valid BusinessUnitVO businessUnitVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, businessUnitService.addBusinessUnit(businessUnitVO));
	}

	@Operation(summary = "Update business unit")
	@PutMapping(path = "{id}")
	public WSResponse<BusinessUnitVO> updateBusinessUnit(@PathVariable @Valid Long id,
			@RequestBody @Valid BusinessUnitVO businessUnitVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				businessUnitService.updateBusinessUnit(id, businessUnitVO));

	}

	@Operation(summary = "Get business unit by Id")
	@GetMapping(path = "{id}")
	public WSResponse<BusinessUnitVO> getBusinessUnitById(@PathVariable @Valid Long id) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, businessUnitService.getBusinessUnitById(id));
	}

	@Operation(summary = "Remove business unit")
	@DeleteMapping(path = "{id}")
	public WSResponse<String> removeBusinessUnit(@PathVariable @Valid Long id) {
		businessUnitService.deleteBusinessUnit(id);
		String data = "Business Unit id  " + id + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get Business Units")
	@GetMapping
	public WSResponse<List<BusinessUnitVO>> getBusinessUnits() {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, businessUnitService.getBusinessUnits());
	}

	@Operation(summary = "Get Business Units By Pagination")
	@GetMapping("/page")
	public WSResponse<List<BusinessUnitVO>> getBusinessUnitsByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "businessUnitId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				businessUnitService.getPagination(pagenumber, pagesize, sortBy));
	}

	@Operation(summary = "Activate or Deactivate BusinessUnit by Id")
	@PutMapping(path = "/activate-or-deactivate/{id}")
	public WSResponse<BusinessUnitVO> activateOrDeactivateBusinessUnitById(@PathVariable Long id) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				businessUnitService.activateOrDeactivateById(id));
	}

}
