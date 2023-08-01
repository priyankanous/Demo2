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
import com.nous.rollingrevenue.service.BillingTypeService;
import com.nous.rollingrevenue.vo.BillingTypeVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/billing-type")
@CrossOrigin(origins = "*")
public class BillingTypeController {

	@Autowired
	BillingTypeService billingTypeService;

	@Operation(summary = "save billing type")
	@PostMapping
	public WSResponse<String> saveBillingType(@RequestBody @Valid BillingTypeVO billingTypeVO) {
		billingTypeService.addBillingType(billingTypeVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Update billing type")
	@PutMapping(path = "{id}")
	public WSResponse<String> updateBillingType(@PathVariable @Valid Long id,
			@RequestBody @Valid BillingTypeVO billingTypeVO) {
		billingTypeService.updateBillingType(id, billingTypeVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Get billing type by Id")
	@GetMapping(path = "{id}")
	public WSResponse<BillingTypeVO> getBillingTypeById(@PathVariable @Valid Long id) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, billingTypeService.getBillingTypeById(id));
	}
	
	@Operation(summary = "Remove billing type")
	@DeleteMapping(path = "{id}")
	public WSResponse<String> removeBillingType(@PathVariable @Valid Long id) {
		billingTypeService.deleteBillingType(id);
		String data = "Billing Type id  " + id + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}
	
	@Operation(summary = "Get Billing Type By Pagination")
	@GetMapping("/page")
	public WSResponse<List<BillingTypeVO>> getBillingTypesByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "billingTypeId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				billingTypeService.getPagination(pagenumber, pagesize, sortBy));
	}
	
	@Operation(summary = "Activate or Deactivate BillingType by Id")
	@PutMapping(path = "/activate-or-deactivate/{id}")
	public WSResponse<String> activateOrDeactivateBillingTypeById(@PathVariable Long id) {
		billingTypeService.activateOrDeactivateById(id);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
}
