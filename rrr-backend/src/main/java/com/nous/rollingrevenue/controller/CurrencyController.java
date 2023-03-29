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
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.CurrencyService;
import com.nous.rollingrevenue.vo.CurrencyVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/currency")
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
	public WSResponse<CurrencyVO> saveCurrency(@RequestBody @Valid CurrencyVO currencyVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, currencyService.saveCurrency(currencyVO));
	}

	@Operation(summary = "Get Currency by Id")
	@GetMapping(path = "{currencyId}")
	public WSResponse<CurrencyVO> getCurrencyById(@PathVariable Long currencyId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, currencyService.getCurrencyById(currencyId));
	}

	@Operation(summary = "Update Currency by Id")
	@PutMapping(path = "{currencyId}")
	public WSResponse<CurrencyVO> updateCurrency(@PathVariable Long currencyId, @RequestBody @Valid CurrencyVO currencyVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				currencyService.updateCurrency(currencyId, currencyVO));
	}

	@Operation(summary = "Delete Currency by Id")
	@DeleteMapping(path = "{currencyId}")
	public WSResponse<String> deleteCurrency(@PathVariable Long currencyId) {
		currencyService.deleteCurrencyById(currencyId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}


}
