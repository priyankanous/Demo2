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
import com.nous.rollingrevenue.service.FinancialYearPermissionService;
import com.nous.rollingrevenue.vo.FinancialYearPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/financial-year-permission")
@CrossOrigin(origins = "*")
public class FinancialYearPermissionController {

	@Autowired
	private FinancialYearPermissionService financialYearPermissionService;

	@Operation(summary = "Save FinancialYearPermission")
	@PostMapping
	public WSResponse<String> saveFinancialYearPermission(
			@RequestBody @Valid FinancialYearPermissionVO financialYearPermissionVO) {
		financialYearPermissionService.saveFinancialYearPermission(financialYearPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update FinancialYearPermission by financialYearPermissionId")
	@PutMapping(path = "{financialYearPermissionId}")
	public WSResponse<String> updateFinancialYearPermissionById(@PathVariable Long financialYearPermissionId,
			@RequestBody @Valid FinancialYearPermissionVO financialYearPermissionVO) {
		financialYearPermissionService.updateFinancialYearPermissionById(financialYearPermissionId,
				financialYearPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete FinancialYearPermission by financialYearPermissionId")
	@DeleteMapping(path = "{financialYearPermissionId}")
	public WSResponse<String> deleteFinancialYearPermission(@PathVariable Long financialYearPermissionId) {
		financialYearPermissionService.deleteFinancialYearPermissionById(financialYearPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get All FinancialYearPermission")
	@GetMapping
	public WSResponse<List<FinancialYearPermissionVO>> getAllFinancialYearPermission() {
		List<FinancialYearPermissionVO> financialYearPermissions = financialYearPermissionService
				.getFinancialYearPermissions();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, financialYearPermissions);
	}

	@Operation(summary = "Get FinancialYearPermission by financialYearPermissionId")
	@GetMapping(path = "{financialYearPermissionId}")
	public WSResponse<FinancialYearPermissionVO> getFinancialYearPermissionById(
			@PathVariable Long financialYearPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				financialYearPermissionService.getFinancialYearPermissionById(financialYearPermissionId));
	}
	
}
