package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.OpportunityService;
import com.nous.rollingrevenue.vo.OpportunityVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/opportunity")
public class OpportunityController {

	@Autowired
	private OpportunityService opportunityService;

	@Operation(summary = "Get All Opportunity")
	@GetMapping
	public WSResponse<List<OpportunityVO>> getAllOpportunity() {
		List<OpportunityVO> opportunityVOs = opportunityService.getAllOpportunity();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, opportunityVOs);
	}

	@Operation(summary = "Save Opportunity")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WSResponse<OpportunityVO> saveOpportunity(@RequestBody @Valid OpportunityVO opportunityVO) {
		return WSResponse.buildWSResponse(HttpStatus.CREATED, RestMessage.SUCCESS,
				opportunityService.saveOpportunity(opportunityVO));
	}

	@Operation(summary = "Get Opportunity by Id")
	@GetMapping(path = "{opportunityId}")
	public WSResponse<OpportunityVO> getOpportunityById(@PathVariable Long opportunityId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				opportunityService.getOpportunityById(opportunityId));
	}

	@Operation(summary = "Update Opportunity by Id")
	@PutMapping(path = "{opportunityId}")
	public WSResponse<OpportunityVO> updateOpportunity(@PathVariable Long opportunityId,
			@RequestBody @Valid OpportunityVO opportunityVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				opportunityService.updateOpportunity(opportunityId, opportunityVO));
	}

	@Operation(summary = "Delete Opportunity by Id")
	@DeleteMapping(path = "{opportunityId}")
	public WSResponse<String> deleteOpportunity(@PathVariable Long opportunityId) {
		opportunityService.deleteOpportunityById(opportunityId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
