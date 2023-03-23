package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.nous.rollingrevenue.service.FinancialYearService;
import com.nous.rollingrevenue.vo.FinancialYearVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/financial-year")
public class FinancialYearController {
	
	@Autowired
	private FinancialYearService financialYearService;
	
	@Operation(summary = "Get All FinancialYear")
	@GetMapping
	public WSResponse<List<FinancialYearVO>> getAllFinancialYear() {
		List<FinancialYearVO> financialYearVOs = financialYearService.getAllFinancialYear();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, financialYearVOs);
	}
	
	@Operation(summary = "Save FinancialYear")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WSResponse<FinancialYearVO> saveFinancialYear(@RequestBody @Valid FinancialYearVO financialYearVO) {
		return WSResponse.buildWSResponse(HttpStatus.CREATED, RestMessage.SUCCESS, financialYearService.saveFinancialYear(financialYearVO));
	}
	
	@Operation(summary = "Get FinancialYear by Id")
	@GetMapping(path = "{financialYearId}")
	public WSResponse<FinancialYearVO> getFinancialYearById(@PathVariable Long financialYearId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, financialYearService.getFinancialYearById(financialYearId));
	}
	
	@Operation(summary = "Update FinancialYear by Id")
	@PutMapping(path = "{financialYearId}")
	public WSResponse<FinancialYearVO> updateFinancialYear(@PathVariable Long financialYearId, @RequestBody @Valid FinancialYearVO financialYearVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				financialYearService.updateFinancialYear(financialYearId, financialYearVO));
	}
	
	@Operation(summary = "Delete FinancialYear by Id")
	@DeleteMapping(path = "{financialYearId}")
	public WSResponse<String> deleteFinancialYear(@PathVariable Long financialYearId) {
		financialYearService.deleteFinancialYearById(financialYearId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
