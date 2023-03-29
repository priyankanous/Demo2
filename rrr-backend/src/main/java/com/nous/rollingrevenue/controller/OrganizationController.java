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
import com.nous.rollingrevenue.service.OrganizationService;
import com.nous.rollingrevenue.vo.OrganizationVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

/**
 *
 * @author Nous Infosystems
 */

@Validated
@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	@Operation(summary = "save organization")
	@Validated
	@PostMapping
	public WSResponse<OrganizationVO> saveOrganization(@RequestBody @Valid OrganizationVO organizationVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, organizationService.addOrganization(organizationVO));
	}

	@Operation(summary = "Update organization")
	@PutMapping(value = "{id}")
	public WSResponse<OrganizationVO> updateOrganization(@PathVariable @Valid Long id,
			@RequestBody @Valid OrganizationVO organizationVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				organizationService.updateOrganization(id, organizationVO));
	}

	@Operation(summary = "Get ogranization by Id")
	@GetMapping(value = "/{id}")
	public WSResponse<OrganizationVO> getOrganization(@PathVariable @Valid Long id) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, organizationService.getOrganization(id));
	}

	@Operation(summary = "Delete organization")
	@DeleteMapping(value = "/{id}")
	public WSResponse<String> removeOrganization(@PathVariable @Valid Long id) {
		organizationService.deleteOrganization(id);
		String data = "Organization id  " + id + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get All organization")
	@GetMapping
	public WSResponse<List<OrganizationVO>> getOrganization() {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, organizationService.getAllOrganization());
	}

}
