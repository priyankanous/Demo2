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
import com.nous.rollingrevenue.service.AnnualTargetEntryPermissionService;
import com.nous.rollingrevenue.vo.AnnualTargetEntryPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/annual-target-entry-permission")
@CrossOrigin(origins = "*")
public class AnnualTargetEntryPermissionController {

	@Autowired
	private AnnualTargetEntryPermissionService annualTargetEntryPermissionService;

	@Operation(summary = "Get All AnnualTargetEntryPermission")
	@GetMapping
	public WSResponse<List<AnnualTargetEntryPermissionVO>> getAllAnnualTargetEntryPermission() {
		List<AnnualTargetEntryPermissionVO> annualTargetEntryPermissionVOs = annualTargetEntryPermissionService.getAllAnnualTargetEntryPermission();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, annualTargetEntryPermissionVOs);
	}

	@Operation(summary = "Save AnnualTargetEntryPermission")
	@PostMapping
	public WSResponse<String> saveAnnualTargetEntryPermission(
			@RequestBody @Valid AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO) {
		annualTargetEntryPermissionService.saveAnnualTargetEntryPermission(annualTargetEntryPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get AnnualTargetEntryPermission by Id")
	@GetMapping(path = "{annualTargetEntryPermissionId}")
	public WSResponse<AnnualTargetEntryPermissionVO> getAnnualTargetEntryPermissionById(@PathVariable Long annualTargetEntryPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				annualTargetEntryPermissionService.getAnnualTargetEntryPermissionById(annualTargetEntryPermissionId));
	}

	@Operation(summary = "Update AnnualTargetEntryPermission by Id")
	@PutMapping(path = "{annualTargetEntryPermissionId}")
	public WSResponse<String> updateAnnualTargetEntryPermission(@PathVariable Long annualTargetEntryPermissionId,
			@RequestBody @Valid AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO) {
		annualTargetEntryPermissionService.updateAnnualTargetEntryPermission(annualTargetEntryPermissionId, annualTargetEntryPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete AnnualTargetEntryPermission by Id")
	@DeleteMapping(path = "{annualTargetEntryPermissionId}")
	public WSResponse<String> deleteAnnualTargetEntryPermission(@PathVariable Long annualTargetEntryPermissionId) {
		annualTargetEntryPermissionService.deleteAnnualTargetEntryPermissionById(annualTargetEntryPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
}
