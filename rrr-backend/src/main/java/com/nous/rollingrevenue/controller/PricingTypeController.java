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
import com.nous.rollingrevenue.service.PricingTypeService;
import com.nous.rollingrevenue.vo.PricingTypeVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pricing-type")
@CrossOrigin(origins = "*")
public class PricingTypeController {
	
	@Autowired
	private PricingTypeService pricingTypeService;
	
	@Operation(summary = "Get All PricingType")
	@GetMapping
	public WSResponse<List<PricingTypeVO>> getAllPricingType() {
		List<PricingTypeVO> pricingTypeVOs = pricingTypeService.getAllPricingType();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, pricingTypeVOs);
	}
	
	@Operation(summary = "Save PricingType")
	@PostMapping
	public WSResponse<PricingTypeVO> savePricingType(@RequestBody @Valid PricingTypeVO pricingTypeVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, pricingTypeService.savePricingType(pricingTypeVO));
	}
	
	@Operation(summary = "Get PricingType by Id")
	@GetMapping(path = "{pricingTypeId}")
	public WSResponse<PricingTypeVO> getPricingTypeById(@PathVariable Long pricingTypeId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, pricingTypeService.getPricingTypeById(pricingTypeId));
	}
	
	@Operation(summary = "Update PricingType by Id")
	@PutMapping(path = "{pricingTypeId}")
	public WSResponse<PricingTypeVO> updatePricingType(@PathVariable Long pricingTypeId, @RequestBody @Valid PricingTypeVO pricingTypeVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				pricingTypeService.updatePricingType(pricingTypeId, pricingTypeVO));
	}
	
	@Operation(summary = "Delete PricingType by Id")
	@DeleteMapping(path = "{pricingTypeId}")
	public WSResponse<String> deletePricingType(@PathVariable Long pricingTypeId) {
		pricingTypeService.deletePricingTypeById(pricingTypeId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get PricingType  By Pagination")
	@GetMapping("/page")
	public WSResponse<List<PricingTypeVO>> getPricingTypeByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "pricingTypeId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, pricingTypeService.getPagination(pagenumber, pagesize, sortBy));
	}
}
