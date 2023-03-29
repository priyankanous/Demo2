package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.nous.rollingrevenue.service.CocPracticeService;
import com.nous.rollingrevenue.vo.CocPracticeVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/cocpractice")
public class CocPracticeController {

	@Autowired
	CocPracticeService cocpracticeService;

	@Operation(summary = "save cocpractice")
	@Validated
	@PostMapping
	public WSResponse<CocPracticeVO> saveCocPractice(@RequestBody @Valid CocPracticeVO cocpracticeVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, cocpracticeService.addCocPractice(cocpracticeVO));
	}

	@Operation(summary = "Update cocpractice")
	@PutMapping(value = "/{id}")
	public WSResponse<CocPracticeVO> updateCocPractice(@PathVariable @Valid Long id,
			@RequestBody @Valid CocPracticeVO cocpracticeVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, cocpracticeService.updateCocPractice(id, cocpracticeVO));

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

}
