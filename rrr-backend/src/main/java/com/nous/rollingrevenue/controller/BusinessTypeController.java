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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.BusinessTypeService;
import com.nous.rollingrevenue.vo.BusinessTypeVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/business-type")
@CrossOrigin(origins = "*")
public class BusinessTypeController {
	
	@Autowired
	private BusinessTypeService businessTypeService;
	
	@Operation(summary = "Get All BusinessType")
	@GetMapping
	public WSResponse<List<BusinessTypeVO>> getAllBusinessType() {
		List<BusinessTypeVO> businessTypeVOs = businessTypeService.getAllBusinessType();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, businessTypeVOs);
	}
	
	@Operation(summary = "Save BusinessType")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WSResponse<BusinessTypeVO> saveBusinessType(@RequestBody @Valid BusinessTypeVO businessTypeVO) {
		return WSResponse.buildWSResponse(HttpStatus.CREATED, RestMessage.SUCCESS, businessTypeService.saveBusinessType(businessTypeVO));
	}
	
	@Operation(summary = "Get BusinessType by Id")
	@GetMapping(path = "{businessTypeId}")
	public WSResponse<BusinessTypeVO> getBusinessTypeById(@PathVariable Long businessTypeId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, businessTypeService.getBusinessTypeById(businessTypeId));
	}
	
	@Operation(summary = "Update BusinessType by Id")
	@PutMapping(path = "{businessTypeId}")
	public WSResponse<BusinessTypeVO> updateBusinessType(@PathVariable Long businessTypeId, @RequestBody @Valid BusinessTypeVO businessTypeVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				businessTypeService.updateBusinessType(businessTypeId, businessTypeVO));
	}
	
	@Operation(summary = "Delete BusinessType by Id")
	@DeleteMapping(path = "{businessTypeId}")
	public WSResponse<String> deleteBusinessType(@PathVariable Long businessTypeId) {
		businessTypeService.deleteBusinessTypeById(businessTypeId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
