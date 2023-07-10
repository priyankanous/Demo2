package com.nous.rollingrevenue.controller;

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
import com.nous.rollingrevenue.service.SBUClientTypeReportService;
import com.nous.rollingrevenue.vo.BusinessTypeResponse;
import com.nous.rollingrevenue.vo.SBUClientTypeReportRequest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/report")
@CrossOrigin(origins = "*")
public class SBUClientTypeReportController {

	@Autowired
	private SBUClientTypeReportService SBUClientTypeReportService;

	@Operation(summary = "Get SBUClientType Report Details")
	@PostMapping(path = "/sbuclientType")
	public WSResponse<BusinessTypeResponse> getSBUClientTypeReportDetails(
			@RequestBody @Valid SBUClientTypeReportRequest SBUClientTypeReportRequest,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, SBUClientTypeReportService
				.getSBUClientTypeReportDetails(SBUClientTypeReportRequest, isDisplayAdditionalQuarter));
	}
}
