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
import com.nous.rollingrevenue.service.RolesPermissionService;
import com.nous.rollingrevenue.vo.RolesPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/roles-permission")
@CrossOrigin(origins = "*")
public class RolesPermissionController {

	@Autowired
	private RolesPermissionService rolesPermissionService;

	@Operation(summary = "Get All RolesPermission")
	@GetMapping
	public WSResponse<List<RolesPermissionVO>> getAllRolesPermission() {
		List<RolesPermissionVO> rolesPermissionVOs = rolesPermissionService.getAllRolesPermission();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, rolesPermissionVOs);
	}

	@Operation(summary = "Save RolesPermission")
	@PostMapping
	public WSResponse<String> saveRolesPermission(@RequestBody @Valid RolesPermissionVO rolesPermissionVO) {
		rolesPermissionService.saveRolesPermission(rolesPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get RolesPermission by Id")
	@GetMapping(path = "{rolesPermissionId}")
	public WSResponse<RolesPermissionVO> getRolesPermissionById(@PathVariable Long rolesPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				rolesPermissionService.getRolesPermissionById(rolesPermissionId));
	}

	@Operation(summary = "Update RolesPermission by Id")
	@PutMapping(path = "{rolesPermissionId}")
	public WSResponse<String> updateRolesPermission(@PathVariable Long rolesPermissionId,
			@RequestBody @Valid RolesPermissionVO rolesPermissionVO) {
		rolesPermissionService.updateRolesPermission(rolesPermissionId, rolesPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete RolesPermission by Id")
	@DeleteMapping(path = "{rolesPermissionId}")
	public WSResponse<String> deleteRolesPermission(@PathVariable Long rolesPermissionId) {
		rolesPermissionService.deleteRolesPermissionById(rolesPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
