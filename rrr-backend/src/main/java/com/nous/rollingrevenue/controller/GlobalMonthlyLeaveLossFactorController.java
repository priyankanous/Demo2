package com.nous.rollingrevenue.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.nous.rollingrevenue.convertor.LeaveLossFactorVOToLeaveLossFactor;
import com.nous.rollingrevenue.model.GlobalMonthlyLeaveLossFactor;
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
	public WSResponse<GlobalMonthlyLeaveLossFactorVO> saveLeaveLossFactor(
			@RequestBody @Valid GlobalMonthlyLeaveLossFactorVO factorVO) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = LeaveLossFactorVOToLeaveLossFactor
				.convertLeaveLossFactorVOToLeaveLossFactor(factorVO);
		leaveLossFactor = globalMonthlyLeaveLossFactorService.addLeaveLossFactor(leaveLossFactor);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				LeaveLossFactorVOToLeaveLossFactor.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor));
	}

	@Operation(summary = "Update leave loss factor")
	@PutMapping(path = "{id}")
	public WSResponse<GlobalMonthlyLeaveLossFactorVO> updateLeaveLossFactor(@PathVariable @Valid Long id,
			@RequestBody @Valid GlobalMonthlyLeaveLossFactorVO factorVO) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = globalMonthlyLeaveLossFactorService.updateLeaveLossFactor(id,
				factorVO);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				LeaveLossFactorVOToLeaveLossFactor.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor));

	}

	@Operation(summary = "Get leave loss factor by Id")
	@GetMapping(path = "{id}")
	public WSResponse<GlobalMonthlyLeaveLossFactorVO> getLeaveLossFactorById(@PathVariable @Valid Long id) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = null;
		leaveLossFactor = globalMonthlyLeaveLossFactorService.getLeaveLossFactorById(id);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				LeaveLossFactorVOToLeaveLossFactor.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor));
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
		List<GlobalMonthlyLeaveLossFactorVO> leaveLossFactors = new ArrayList<>();
		globalMonthlyLeaveLossFactorService.getLeaveLossFactors().stream().forEach(e -> {
			leaveLossFactors.add(LeaveLossFactorVOToLeaveLossFactor.convertLeaveLossFactorToLeaveLossFactorVO(e));

		});
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, leaveLossFactors);
	}

}
