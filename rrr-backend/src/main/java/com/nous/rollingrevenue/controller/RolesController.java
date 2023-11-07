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
import com.nous.rollingrevenue.service.RolesService;
import com.nous.rollingrevenue.vo.RolesVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin(origins = "*")
public class RolesController {

	@Autowired
	private RolesService roleService;

	@Operation(summary = "Save Roles")
	@PostMapping
	public WSResponse<String> saveRolesDetails(@RequestBody @Valid RolesVO rolesVO) {
		roleService.saveRolesDetails(rolesVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "update Roles")
	@PutMapping(path = "/{roleId}")
	public WSResponse<String> updateRolesDetails(@PathVariable @Valid Long roleId,
			@RequestBody @Valid RolesVO rolesVO) {
		roleService.updateRolesDetails(roleId, rolesVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete Role by Id")
	@DeleteMapping(path = "{roleId}")
	public WSResponse<String> deleteRoles(@PathVariable Long roleId) {
		roleService.deleteRolesById(roleId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get Roles by Id")
	@GetMapping(path = "{roleId}")
	public WSResponse<RolesVO> getRolesById(@PathVariable Long roleId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, roleService.getRolesById(roleId));
	}

	@Operation(summary = "Get All Roles")
	@GetMapping
	public WSResponse<List<RolesVO>> getAllRoles() {
		List<RolesVO> rolesVOs = roleService.getAllRoles();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, rolesVOs);
	}

}
