package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
import com.nous.rollingrevenue.service.CocPracticeService;
import com.nous.rollingrevenue.vo.CocPracticeVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/cocpractice")
@CrossOrigin(origins = "*")
public class CocPracticeController {

	@Autowired
	CocPracticeService cocpracticeService;

	@Operation(summary = "save cocpractice")
	@Validated
	@PostMapping
	public WSResponse<String> saveCocPractice(@RequestBody @Valid CocPracticeVO cocpracticeVO) {
		cocpracticeService.addCocPractice(cocpracticeVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update cocpractice")
	@PutMapping(value = "/{id}")
	public WSResponse<String> updateCocPractice(@PathVariable @Valid Long id,
			@RequestBody @Valid CocPracticeVO cocpracticeVO) {
		cocpracticeService.updateCocPractice(id, cocpracticeVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);

	}

	@Operation(summary = "Get cocpractice by Id")
	@GetMapping(value = "/{id}")
	public WSResponse<CocPracticeVO> getCocPractice(@PathVariable @Valid Long id) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, cocpracticeService.getCocPractice(id));
	}

	@Operation(summary = "Delete cocpractice")
	@DeleteMapping(value = "/{id}")
	public WSResponse<String> removeCocPractice(@PathVariable @Valid Long id) {
		cocpracticeService.deleteCocPractice(id);
		String data = "CocPractice id  " + id + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get All cocpractice")
	@GetMapping
	public WSResponse<List<CocPracticeVO>> getCocPractice() {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, cocpracticeService.getAllCocPractice());
	}

	@Operation(summary = "Get CocPractice By Pagination")
	@GetMapping("/page")
	public WSResponse<List<CocPracticeVO>> getCocPracticeByPagination(@RequestParam(defaultValue = "1") int pagenumber,
			@RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "cocPracticeId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				cocpracticeService.getPagination(pagenumber, pagesize, sortBy));
	}

	@Operation(summary = "Activate or Deactivate CocPractice by Id")
	@PutMapping(path = "/activate-or-deactivate/{id}")
	public WSResponse<String> activateOrDeactivateCocPracticeById(@PathVariable Long id) {
		cocpracticeService.activateOrDeactivateById(id);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get COC by BU")
	@GetMapping(path = "/businessUnitId/{businessUnitId}")
	public WSResponse<List<CocPracticeVO>> getCocPracticeBybusinessUnitId(@PathVariable Long businessUnitId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				cocpracticeService.getCocPracticeBybusinessUnitId(businessUnitId));
	}
}
