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
import com.nous.rollingrevenue.service.ClientTypeReportService;
import com.nous.rollingrevenue.vo.BusinessTypeResponse;
import com.nous.rollingrevenue.vo.ClientTypeReportRequest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/report")
@CrossOrigin(origins = "*")
public class ClientTypeController {

	@Autowired
	private ClientTypeReportService clientTypeReportService;

	@Operation(summary = "Get BusinessType Report Details")
	@PostMapping(path = "/clienttype")
	public WSResponse<BusinessTypeResponse> getClientTypeReporDetails(
			@RequestBody @Valid ClientTypeReportRequest clientTypeReportRequest,
			@RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				clientTypeReportService.getClientTypeReporDetails(clientTypeReportRequest, isDisplayAdditionalQuarter));
	}

}
