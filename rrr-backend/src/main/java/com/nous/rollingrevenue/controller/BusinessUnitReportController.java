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
import com.nous.rollingrevenue.service.BusinessUnitReportService;
import com.nous.rollingrevenue.vo.BusinessUnitReportRequest;
import com.nous.rollingrevenue.vo.BusinessUnitResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/report")
@CrossOrigin(origins = "*")
public class BusinessUnitReportController {

	@Autowired
	private BusinessUnitReportService businessUnitReportService;

	@Operation(summary = "Get BusinessUnit Report Details")
	@PostMapping(path = "/businessunit")
	public WSResponse<BusinessUnitResponse> getBusinessUnitReportDetails(
			@RequestBody @Valid BusinessUnitReportRequest businessUnitReportRequest,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, businessUnitReportService
				.getBusinessUnitReportDetails(businessUnitReportRequest, isDisplayAdditionalQuarter));
	}

}
