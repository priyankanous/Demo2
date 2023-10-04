package com.nous.rollingrevenue.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.RevenueService;
import com.nous.rollingrevenue.vo.DeleteRevenueResourceResponse;
import com.nous.rollingrevenue.vo.FPRevenueEntryVO;
import com.nous.rollingrevenue.vo.OpportunityEntryResponse;
import com.nous.rollingrevenue.vo.OpportunityRevenueRequest;
import com.nous.rollingrevenue.vo.ResourceDeleteRequest;
import com.nous.rollingrevenue.vo.ResourceEntryRequest;
import com.nous.rollingrevenue.vo.ResourceEntryResponse;
import com.nous.rollingrevenue.vo.ResourceRevenueRequest;
import com.nous.rollingrevenue.vo.ResourceRevenueResponse;
import com.nous.rollingrevenue.vo.RevenueEntryResponse;
import com.nous.rollingrevenue.vo.RollingRevenueResponse;
import com.nous.rollingrevenue.vo.TandMRevenueEntryVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/revenue-entry")
@CrossOrigin(origins = "*")
public class RevenueController {

	@Autowired
	private RevenueService revenueService;

	@Operation(summary = "Save TandMRevenueEntry")
	@PostMapping(path = "/TandM")
	public WSResponse<String> saveTandMRevenueEntry(@RequestBody @Valid TandMRevenueEntryVO tmRevenueEntry) {
		revenueService.saveTandMRevenueEntry(tmRevenueEntry);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Save FPRevenueEntry")
	@PostMapping(path = "/fixed-price")
	public WSResponse<String> saveFPRevenueEntry(@RequestBody @Valid FPRevenueEntryVO fpRevenueEntry) {
		revenueService.saveFPRevenueEntry(fpRevenueEntry);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get All RevenueEntries")
	@GetMapping(path = "{financialYearName}")
	public WSResponse<RevenueEntryResponse> getAllRevenueEntries(@PathVariable String financialYearName,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.getRevenueEntries(financialYearName, isDisplayAdditionalQuarter));
	}

	@Operation(summary = "Get Opportunities")
	@PostMapping(path = "/opportunity")
	public WSResponse<OpportunityEntryResponse> getOpportunities(
			@RequestBody @Valid OpportunityRevenueRequest opportunityRevenueRequest,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.getOpportunities(opportunityRevenueRequest, isDisplayAdditionalQuarter));
	}

	@Operation(summary = "Get Resources")
	@PostMapping(path = "/resources")
	public WSResponse<ResourceEntryResponse> getResources(@RequestBody @Valid ResourceEntryRequest resourceEntryRequest,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.getResourcesByOpportunity(resourceEntryRequest, isDisplayAdditionalQuarter));
	}

	@Operation(summary = "Get Resources Revenue")
	@PostMapping(path = "/resourcerevenue")
	public WSResponse<ResourceRevenueResponse> getResourceRevenue(
			@RequestBody @Valid ResourceRevenueRequest resourceRevenueRequest,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.getResourceRevenue(resourceRevenueRequest, isDisplayAdditionalQuarter));
	}

	@Operation(summary = "Update FPRevenueEntry")
	@PutMapping(path = "/fixed-price/{opportunityId}")
	public WSResponse<String> updateFPRevenueEntry(@PathVariable Long opportunityId,
			@RequestBody @Valid FPRevenueEntryVO fpRevenueEntry) {
		revenueService.updateFPRevenueEntry(opportunityId, fpRevenueEntry);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update TandMRevenueEntry")
	@PutMapping(path = "/TandM/{opportunityId}")
	public WSResponse<String> updateTandMRevenueEntry(@PathVariable @Valid Long opportunityId,
			@RequestBody @Valid TandMRevenueEntryVO tandMRevenueEntryVO) {
		revenueService.updateTandMRevenueEntry(opportunityId, tandMRevenueEntryVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);

	}

	@Operation(summary = "Get RevenueEntry By Id")
	@GetMapping(path = "/getbyid/{opportunityId}")
	public WSResponse<RollingRevenueResponse> getRevenueEntriesDetailsById(@PathVariable @Valid Long opportunityId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.getRevenueEntryDetailsById(opportunityId));
	}

	@Operation(summary = "Delete RevenueEntry By Id")
	@DeleteMapping(path = "/{opportunityId}")
	public WSResponse<String> deleteRevenueEntriesDetailsById(@PathVariable @Valid Long opportunityId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.deleteRevenueEntriesDetailsById(opportunityId));
	}

	@Operation(summary = "Get RevenueEntry By Pagination")
	@GetMapping(path = "/page/{financialYearName}")
	public WSResponse<RevenueEntryResponse> getRevenueEntriesDetailsByPagination(@PathVariable String financialYearName,
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "revenueResourceEntryId", required = false) String sortBy,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.getRevenueEntriesDetailsByPagination(financialYearName, pagenumber, pagesize, sortBy,
						isDisplayAdditionalQuarter));
	}

	@Operation(summary = "Delete Resource Details")
	@DeleteMapping(path = "/delete/resources")
	public WSResponse<String> deleteResourcesDetails(@RequestBody @Valid ResourceDeleteRequest resourceDeleteRequest) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.deleteResourcesDetails(resourceDeleteRequest));
	}

	@Operation(summary = "Delete RevenueResourceEntry By revenueResourceEntryId")
	@DeleteMapping(path = "/revenueresource/{revenueResourceEntryId}")
	public WSResponse<DeleteRevenueResourceResponse> deleteRevenueResourceEntry(
			@PathVariable @Valid Long revenueResourceEntryId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.deleteRevenueResourceEntry(revenueResourceEntryId));
	}

}
