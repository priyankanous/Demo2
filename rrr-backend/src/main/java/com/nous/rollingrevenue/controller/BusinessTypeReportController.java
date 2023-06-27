package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.service.BusinessTypeReportService;
import com.nous.rollingrevenue.vo.BusinessTypeReportRequest;
import com.nous.rollingrevenue.vo.BusinessTypeResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/report")
@CrossOrigin(origins = "*")
public class BusinessTypeReportController {

	@Autowired
	private BusinessTypeReportService businessTypeReportService;

	@Operation(summary = "Get BusinessType Report Details")
	@PostMapping(path = "/businesstype")
	public WSResponse<BusinessTypeResponse> getBusinessTypeReportDetails(
			@RequestBody @Valid BusinessTypeReportRequest businessTypeReportRequest,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, businessTypeReportService
				.getBusinessTypeReportDetails(businessTypeReportRequest, isDisplayAdditionalQuarter));
	}

	// Dummy API for Get Revenue Resources Entries List //
	// TODO: Delete after above API working successfully
	@PostMapping(path = "/businesstype/param")
	public WSResponse<List<RevenueResourceEntry>> getRevenueResourceEntryFilter(
			@RequestBody @Valid BusinessTypeReportRequest businessTypeReportRequest,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, businessTypeReportService
				.getRevenueResourceEntryFilter(businessTypeReportRequest, isDisplayAdditionalQuarter));
	}

}
