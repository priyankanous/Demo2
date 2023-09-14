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
import com.nous.rollingrevenue.service.RollingRevenueEntryPermissionService;
import com.nous.rollingrevenue.vo.RollingRevenueEntryPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/rolling-revenue-entry-permission")
@CrossOrigin(origins = "*")
public class RollingRevenueEntryPermissionController {

	@Autowired
	private RollingRevenueEntryPermissionService rollingRevenueEntryPermissionService;

	@Operation(summary = "Save RollingRevenueEntryPermission")
	@PostMapping
	public WSResponse<String> saveRollingRevenueEntryPermission(
			@RequestBody @Valid RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO) {
		rollingRevenueEntryPermissionService.saveRollingRevenueEntryPermission(rollingRevenueEntryPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update RollingRevenueEntryPermission by rollingrevenueEntryPermissionId")
	@PutMapping(path = "{rollingrevenueEntryPermissionId}")
	public WSResponse<String> updateRollingRevenueEntryPermissionById(
			@PathVariable Long rollingrevenueEntryPermissionId,
			@RequestBody @Valid RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO) {
		rollingRevenueEntryPermissionService.updateRollingRevenueEntryPermissionById(rollingrevenueEntryPermissionId,
				rollingRevenueEntryPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete RollingRevenueEntryPermission by rollingrevenueEntryPermissionId")
	@DeleteMapping(path = "{rollingrevenueEntryPermissionId}")
	public WSResponse<String> deleteRollingRevenueEntryPermission(
			@PathVariable Long rollingrevenueEntryPermissionId) {
		rollingRevenueEntryPermissionService.deleteRollingRevenueEntryPermissionById(rollingrevenueEntryPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get All RollingRevenueEntryPermission")
	@GetMapping
	public WSResponse<List<RollingRevenueEntryPermissionVO>> getAllRollingRevenueEntryPermission() {
		List<RollingRevenueEntryPermissionVO> rollingRevenueEntryPermissions = rollingRevenueEntryPermissionService
				.getAllRollingRevenueEntryPermissions();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, rollingRevenueEntryPermissions);
	}

	@Operation(summary = "Get RollingRevenueEntryPermission by rollingrevenueEntryPermissionId")
	@GetMapping(path = "{rollingrevenueEntryPermissionId}")
	public WSResponse<RollingRevenueEntryPermissionVO> getRollingRevenueEntryPermissionById(
			@PathVariable Long rollingrevenueEntryPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, rollingRevenueEntryPermissionService
				.getRollingRevenueEntryPermissionById(rollingrevenueEntryPermissionId));
	}
}
