package com.nous.rollingrevenue.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.convertor.OrganizationVOToOrganization;
import com.nous.rollingrevenue.model.Organization;
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
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	@Operation(summary = "save organization")
	@Validated
	@PostMapping(value = "/organization")
	public WSResponse<OrganizationVO> saveOrganization(@RequestBody @Valid OrganizationVO organizationVO) {
		Organization organization = OrganizationVOToOrganization.convertOrganizationVOToOrganization(organizationVO);
		organization = organizationService.addOrganization(organization);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				OrganizationVOToOrganization.convertOrganizationToOrganizationVO(organization));
	}

	@Operation(summary = "Update organization")
	@PutMapping(value = "/organization/{id}")
	public WSResponse<OrganizationVO> updateOrganization(@PathVariable @Valid Long id, @RequestBody @Valid OrganizationVO organizationVO) {
		Organization organization = organizationService.updateOrganization(id,organizationVO);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				OrganizationVOToOrganization.convertOrganizationToOrganizationVO(organization));

	}

	@Operation(summary = "Get ogranization by Id")
	@GetMapping(value = "/organization/{id}")
	public WSResponse<OrganizationVO> getOrganization(@PathVariable @Valid Long id) {
		Organization organization = null;
		organization = organizationService.getOrganization(id);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				OrganizationVOToOrganization.convertOrganizationToOrganizationVO(organization));
	}

	@Operation(summary = "Delete organization")
	@DeleteMapping(value = "/organization/{id}")
	public WSResponse<String> removeOrganization(@PathVariable @Valid Long id) {
		organizationService.deleteOrganization(id);
		String data = "Organization id  " + id + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get All organization")
	@GetMapping(value = "/organization")
	public WSResponse<List<OrganizationVO>> getOrganization() {
		List<OrganizationVO> organization = new ArrayList<>();
		organizationService.getAllOrganization().stream().forEach(e -> {
			organization.add(OrganizationVOToOrganization.convertOrganizationToOrganizationVO(e));
		});
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, organization);
	}

}
