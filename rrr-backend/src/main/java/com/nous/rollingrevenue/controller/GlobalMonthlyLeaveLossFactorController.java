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
import com.nous.rollingrevenue.service.GlobalMonthlyLeaveLossFactorService;
import com.nous.rollingrevenue.vo.GlobalMonthlyLeaveLossFactorVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/leave-loss-factor")
@CrossOrigin(origins = "*")
public class GlobalMonthlyLeaveLossFactorController {

	@Autowired
	private GlobalMonthlyLeaveLossFactorService globalMonthlyLeaveLossFactorService;

	@Operation(summary = "save leave loss factor")
	@PostMapping
	public WSResponse<String> saveLeaveLossFactor(
			@RequestBody @Valid GlobalMonthlyLeaveLossFactorVO factorVO) {
		globalMonthlyLeaveLossFactorService.addLeaveLossFactor(factorVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update leave loss factor")
	@PutMapping(path = "{id}")
	public WSResponse<String> updateLeaveLossFactor(@PathVariable @Valid Long id,
			@RequestBody @Valid GlobalMonthlyLeaveLossFactorVO factorVO) {
		globalMonthlyLeaveLossFactorService.updateLeaveLossFactor(id, factorVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get leave loss factor by Id")
	@GetMapping(path = "{id}")
	public WSResponse<GlobalMonthlyLeaveLossFactorVO> getLeaveLossFactorById(@PathVariable @Valid Long id) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				globalMonthlyLeaveLossFactorService.getLeaveLossFactorById(id));
	}

	@Operation(summary = "Get leave loss factor by FinancialYear")
	@GetMapping(path = "/financial-year/{financialYear}")
	public WSResponse<List<GlobalMonthlyLeaveLossFactorVO>> getLeaveLossFactorByFinancialYear(
			@PathVariable @Valid String financialYear) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				globalMonthlyLeaveLossFactorService.getLeaveLossFactorByFinancialYear(financialYear));
	}

	@Operation(summary = "Remove leave loss factor")
	@DeleteMapping(path = "{id}")
	public WSResponse<String> removeLeaveLossFactor(@PathVariable @Valid Long id) {
		globalMonthlyLeaveLossFactorService.deleteLeaveLossFactor(id);
		String data = "Global monthly leave loss factor id  " + id + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get leave loss factors")
	@GetMapping
	public WSResponse<List<GlobalMonthlyLeaveLossFactorVO>> getLeaveLossFactors() {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				globalMonthlyLeaveLossFactorService.getLeaveLossFactors());
	}

	@Operation(summary = "Get  leave loss factors By Pagination")
	@GetMapping("/page")
	public WSResponse<List<GlobalMonthlyLeaveLossFactorVO>> getGlobalMonthlyLeaveLossFactorByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "leaveLossFactorId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				globalMonthlyLeaveLossFactorService.getPagination(pagenumber, pagesize, sortBy));
	}

	@Operation(summary = "Activate or Deactivate GlobalMonthlyLeaveLossFactor by Id")
	@PutMapping(path = "/activate-or-deactivate/{leaveLossFactorId}")
	public WSResponse<String> activateOrDeactivateGlobalMonthlyLeaveLossFactorById(
			@PathVariable Long leaveLossFactorId) {
		globalMonthlyLeaveLossFactorService.activateOrDeactivateById(leaveLossFactorId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Save List of Global Monthly Leave loss Factor")
	@PostMapping(path = "/save-all")
	public WSResponse<String> saveListOfGlobalLeaveLossFactor(@RequestBody @Valid List<GlobalMonthlyLeaveLossFactorVO> globalMonthlyLeaveLossFactorVOs) {
		globalMonthlyLeaveLossFactorService.saveListOfGlobalLeaveLossFactor(globalMonthlyLeaveLossFactorVOs);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
