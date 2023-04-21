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
import com.nous.rollingrevenue.service.ProbabilityTypeService;
import com.nous.rollingrevenue.vo.ProbabilityTypeVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/probability-type")
@CrossOrigin(origins = "*")
public class ProbabilityTypeController {

	@Autowired
	private ProbabilityTypeService probabilityTypeService;

	@Operation(summary = "Get All ProbabilityType")
	@GetMapping
	public WSResponse<List<ProbabilityTypeVO>> getAllProbabilityType() {
		List<ProbabilityTypeVO> probabilityTypeVOs = probabilityTypeService.getAllProbabilityType();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, probabilityTypeVOs);
	}

	@Operation(summary = "Save ProbabilityType")
	@PostMapping
	public WSResponse<String> saveProbabilityType(@RequestBody @Valid ProbabilityTypeVO probabilityTypeVO) {
		probabilityTypeService.saveProbabilityType(probabilityTypeVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get ProbabilityType by Id")
	@GetMapping(path = "{probabilityTypeId}")
	public WSResponse<ProbabilityTypeVO> getProbabilityTypeById(@PathVariable Long probabilityTypeId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				probabilityTypeService.getProbabilityTypeById(probabilityTypeId));
	}

	@Operation(summary = "Update ProbabilityType by Id")
	@PutMapping(path = "{probabilityTypeId}")
	public WSResponse<String> updateProbabilityType(@PathVariable Long probabilityTypeId,
			@RequestBody @Valid ProbabilityTypeVO probabilityTypeVO) {
		probabilityTypeService.updateProbabilityType(probabilityTypeId, probabilityTypeVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete ProbabilityType by Id")
	@DeleteMapping(path = "{probabilityTypeId}")
	public WSResponse<String> deleteProbabilityType(@PathVariable Long probabilityTypeId) {
		probabilityTypeService.deleteProbabilityTypeById(probabilityTypeId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get ProbabilityType By Pagination")
	@GetMapping("/page")
	public WSResponse<List<ProbabilityTypeVO>> getProbabilityTypeByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "probabilityTypeId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				probabilityTypeService.getPagination(pagenumber, pagesize, sortBy));
	}

	@Operation(summary = "Activate or Deactivate ProbabilityType by Id")
	@PutMapping(path = "/activate-or-deactivate/{probabilityTypeId}")
	public WSResponse<String> activateOrDeactivateProbabilityTypeById(@PathVariable Long probabilityTypeId) {
		probabilityTypeService.activateOrDeactivateById(probabilityTypeId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
