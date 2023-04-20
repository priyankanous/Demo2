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
import com.nous.rollingrevenue.service.OrganizationService;
import com.nous.rollingrevenue.vo.OrganizationVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/organization")
@CrossOrigin(origins = "*")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	@Operation(summary = "save organization")
	@Validated
	@PostMapping
	public WSResponse<String> saveOrganization(@RequestBody @Valid OrganizationVO organizationVO) {
		organizationService.addOrganization(organizationVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update organization")
	@PutMapping(value = "{id}")
	public WSResponse<String> updateOrganization(@PathVariable @Valid Long id,
			@RequestBody @Valid OrganizationVO organizationVO) {
		organizationService.updateOrganization(id, organizationVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
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

	@Operation(summary = "Get organization By Pagination")
	@GetMapping("/page")
	public WSResponse<List<OrganizationVO>> getOrganizationByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "id", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				organizationService.getPagination(pagenumber, pagesize, sortBy));
	}

	@Operation(summary = "Activate or Deactivate Organization by Id")
	@PutMapping(path = "/activate-or-deactivate/{id}")
	public WSResponse<String> activateOrDeactivateOrganizationById(@PathVariable Long id) {
		organizationService.activateOrDeactivateById(id);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
