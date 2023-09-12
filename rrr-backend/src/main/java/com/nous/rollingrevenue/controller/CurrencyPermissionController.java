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
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.CurrencyPermissionService;
import com.nous.rollingrevenue.vo.CurrencyPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/currency-permission")
@CrossOrigin(origins = "*")
public class CurrencyPermissionController {

	@Autowired
	private CurrencyPermissionService currencyPermissionService;

	@Operation(summary = "Get All  CurrencyPermission")
	@GetMapping
	public WSResponse<List<CurrencyPermissionVO>> getAllCurrencyPermission() {
		List<CurrencyPermissionVO> currencyPermission = currencyPermissionService.getAllCurrencyPermission();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, currencyPermission);
	}

	@Operation(summary = "Save CurrencyPermission")
	@PostMapping
	public WSResponse<String> saveCurrencyPermission(@RequestBody @Valid CurrencyPermissionVO currencyPermissionVO) {
		currencyPermissionService.saveCurrencyPermission(currencyPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get CurrencyPermission by currencyPermissionId")
	@GetMapping(path = "{currencyPermissionId}")
	public WSResponse<CurrencyPermissionVO> getCurrencyPermissionById(@PathVariable Long currencyPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				currencyPermissionService.getCurrencyPermissionById(currencyPermissionId));
	}

	@Operation(summary = "Update CurrencyPermission by currencyPermissionId")
	@PutMapping(path = "{currencyPermissionId}")
	public WSResponse<String> updatecurrencyPermission(@PathVariable Long currencyPermissionId,
			@RequestBody @Valid CurrencyPermissionVO currencyPermissionVO) {
		currencyPermissionService.updateCurrencyPermission(currencyPermissionId, currencyPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete CurrencyPermission by Id")
	@DeleteMapping(path = "{currencyPermissionId}")
	public WSResponse<String> deleteCurrencyPermission(@PathVariable Long currencyPermissionId) {
		currencyPermissionService.deleteCurrencyPermissionById(currencyPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
}
