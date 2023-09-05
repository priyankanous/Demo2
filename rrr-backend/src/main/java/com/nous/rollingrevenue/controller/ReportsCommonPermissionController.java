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
import com.nous.rollingrevenue.service.ReportsCommonPermissionService;
import com.nous.rollingrevenue.vo.ReportsCommonPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reports-common-permission")
@CrossOrigin(origins = "*")
public class ReportsCommonPermissionController {

	@Autowired
	private ReportsCommonPermissionService reportsCommonPermissionService;

	@Operation(summary = "Save ReportsCommonPermission")
	@PostMapping
	public WSResponse<Boolean> saveReportsCommonPermission(
			@RequestBody @Valid ReportsCommonPermissionVO reportsCommonPermissionVO) {
		reportsCommonPermissionService.saveReportsCommonPermission(reportsCommonPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Update ReportsCommonPermission by reportsCommonPermissionId")
	@PutMapping(path = "{reportsCommonPermissionId}")
	public WSResponse<Boolean> updateReportsCommonPermissionById(
			@PathVariable Long reportsCommonPermissionId,
			@RequestBody @Valid ReportsCommonPermissionVO reportsCommonPermissionVO) {
		reportsCommonPermissionService.updateReportsCommonPermissionById(reportsCommonPermissionId,
				reportsCommonPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Delete ReportsCommonPermission by reportsCommonPermissionId")
	@DeleteMapping(path = "{reportsCommonPermissionId}")
	public WSResponse<Boolean> deleteReportsCommonPermission(
			@PathVariable Long reportsCommonPermissionId) {
		reportsCommonPermissionService.deleteReportsCommonPermissionById(reportsCommonPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Get All ReportsCommonPermission")
	@GetMapping
	public WSResponse<List<ReportsCommonPermissionVO>> getAllReportsCommonPermission() {
		List<ReportsCommonPermissionVO> reportsCommonPermissions = reportsCommonPermissionService
				.getAllReportsCommonPermissions();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, reportsCommonPermissions);
	}
	
	@Operation(summary = "Get ReportsCommonPermission by reportsCommonPermissionId")
	@GetMapping(path = "{reportsCommonPermissionId}")
	public WSResponse<ReportsCommonPermissionVO> getReportsCommonPermissionById(
			@PathVariable Long reportsCommonPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, reportsCommonPermissionService
				.getReportsCommonPermissionById(reportsCommonPermissionId));
	}

}
