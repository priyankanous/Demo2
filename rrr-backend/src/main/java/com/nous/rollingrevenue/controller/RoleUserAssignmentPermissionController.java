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
import com.nous.rollingrevenue.service.RoleUserAssignmentPermissionService;
import com.nous.rollingrevenue.vo.RoleUserAssignmentPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/role-user-assignment-permission")
@CrossOrigin(origins = "*")
public class RoleUserAssignmentPermissionController {

	@Autowired
	private RoleUserAssignmentPermissionService roleUserAssignmentPermissionService;

	@Operation(summary = "Get All  RoleUserAssignmentPermission")
	@GetMapping
	public WSResponse<List<RoleUserAssignmentPermissionVO>> getAllRoleUserAssignmentPermission() {
		List<RoleUserAssignmentPermissionVO> roleUserAssignmentPermission = roleUserAssignmentPermissionService.getAllRoleUserAssignmentPermission();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, roleUserAssignmentPermission);
	}

	@Operation(summary = "Save RoleUserAssignmentPermission")
	@PostMapping
	public WSResponse<String> saveRoleUserAssignmentPermission(@RequestBody @Valid RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO) {
		roleUserAssignmentPermissionService.saveRoleUserAssignmentPermission(roleUserAssignmentPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get RoleUserAssignmentPermission by RoleUserAssignmentPermissionId")
	@GetMapping(path = "{roleUserAssignmentPermissionId}")
	public WSResponse<RoleUserAssignmentPermissionVO> getRoleUserAssignmentPermissionById(@PathVariable Long roleUserAssignmentPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				roleUserAssignmentPermissionService.getRoleUserAssignmentPermissionById(roleUserAssignmentPermissionId));
	}

	@Operation(summary = "Update RoleUserAssignmentPermission by roleUserAssignmentPermissionId")
	@PutMapping(path = "{roleUserAssignmentPermissionId}")
	public WSResponse<String> updateroleUserAssignmentPermission(@PathVariable Long roleUserAssignmentPermissionId,
			@RequestBody @Valid RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO) {
		roleUserAssignmentPermissionService.updateRoleUserAssignmentPermission(roleUserAssignmentPermissionId, roleUserAssignmentPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete RoleUserAssignmentPermission by Id")
	@DeleteMapping(path = "{roleUserAssignmentPermissionId}")
	public WSResponse<String> deleteRoleUserAssignmentPermission(@PathVariable Long roleUserAssignmentPermissionId) {
		roleUserAssignmentPermissionService.deleteRoleUserAssignmentPermissionById(roleUserAssignmentPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}


}
