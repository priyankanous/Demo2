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
import com.nous.rollingrevenue.service.CurrencyService;
import com.nous.rollingrevenue.vo.CurrencyVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/currency")
@CrossOrigin(origins = "*")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	@Operation(summary = "Get All Currency")
	@GetMapping
	public WSResponse<List<CurrencyVO>> getAllCurrency() {
		List<CurrencyVO> CurrencyVOs = currencyService.getAllCurrency();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, CurrencyVOs);
	}

	@Operation(summary = "Save Currency")
	@PostMapping
	public WSResponse<String> saveCurrency(@RequestBody @Valid CurrencyVO currencyVO) {
		currencyService.saveCurrency(currencyVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get Currency by Id")
	@GetMapping(path = "{currencyId}")
	public WSResponse<CurrencyVO> getCurrencyById(@PathVariable Long currencyId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				currencyService.getCurrencyById(currencyId));
	}

	@Operation(summary = "Update Currency by Id")
	@PutMapping(path = "{currencyId}")
	public WSResponse<String> updateCurrency(@PathVariable Long currencyId,
			@RequestBody @Valid CurrencyVO currencyVO) {
		currencyService.updateCurrency(currencyId, currencyVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete Currency by Id")
	@DeleteMapping(path = "{currencyId}")
	public WSResponse<String> deleteCurrency(@PathVariable Long currencyId) {
		currencyService.deleteCurrencyById(currencyId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get Currency By Pagination")
	@GetMapping("/page")
	public WSResponse<List<CurrencyVO>> getCurrencyByPagination(@RequestParam(defaultValue = "1") int pagenumber,
			@RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "currencyId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				currencyService.getPagination(pagenumber, pagesize, sortBy));
	}

	@Operation(summary = "Activate or Deactivate Currency by Id")
	@PutMapping(path = "/activate-or-deactivate/{currencyId}")
	public WSResponse<String> activateOrDeactivateCurrencyById(@PathVariable Long currencyId) {
		currencyService.activateOrDeactivateById(currencyId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Get Currency By FinancialYear")
	@GetMapping(path = "/financialyear/{year}")
	public WSResponse<List<CurrencyVO>> getCurrencyByFinancialYear(
			@PathVariable @Valid String year) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				currencyService.getCurrencyByFinancialYear(year));
	}
	
	@Operation(summary = "Save List of Currency")
	@PostMapping(path = "/save-all")
	public WSResponse<String> saveListOfCurrency(@RequestBody @Valid List<CurrencyVO> currencyVOs) {
		currencyService.saveListOfCurrency(currencyVOs);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
