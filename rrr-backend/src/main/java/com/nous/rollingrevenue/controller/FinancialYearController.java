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
import com.nous.rollingrevenue.service.FinancialYearService;
import com.nous.rollingrevenue.vo.FinancialYearVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/financial-year")
@CrossOrigin(origins = "*")
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
	public WSResponse<String> saveFinancialYear(@RequestBody @Valid FinancialYearVO financialYearVO) {
		financialYearService.saveFinancialYear(financialYearVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get FinancialYear by Id")
	@GetMapping(path = "{financialYearId}")
	public WSResponse<FinancialYearVO> getFinancialYearById(@PathVariable Long financialYearId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				financialYearService.getFinancialYearById(financialYearId));
	}

	@Operation(summary = "Update FinancialYear by Id")
	@PutMapping(path = "{financialYearId}")
	public WSResponse<String> updateFinancialYear(@PathVariable Long financialYearId,
			@RequestBody @Valid FinancialYearVO financialYearVO) {
		financialYearService.updateFinancialYear(financialYearId, financialYearVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete FinancialYear by Id")
	@DeleteMapping(path = "{financialYearId}")
	public WSResponse<String> deleteFinancialYear(@PathVariable Long financialYearId) {
		financialYearService.deleteFinancialYearById(financialYearId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get FinancialYear By Pagination")
	@GetMapping("/page")
	public WSResponse<List<FinancialYearVO>> getFinancialYearByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "financialYearId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				financialYearService.getPagination(pagenumber, pagesize, sortBy));
	}

	@Operation(summary = "Activate or Deactivate FinancialYear by Id")
	@PutMapping(path = "/activate-or-deactivate/{financialYearId}")
	public WSResponse<String> activateOrDeactivateFinancialYearById(@PathVariable Long financialYearId) {
		financialYearService.activateOrDeactivateById(financialYearId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
