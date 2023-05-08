package com.nous.rollingrevenue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.RevenueService;
import com.nous.rollingrevenue.vo.FPRevenueEntryVO;
import com.nous.rollingrevenue.vo.RevenueEntryResponse;
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
	public WSResponse<RevenueEntryResponse> getAllRevenueEntries(@PathVariable String financialYearName, @RequestParam(required = false) boolean isDisplayAdditionalQuarter) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueService.getRevenueEntries(financialYearName, isDisplayAdditionalQuarter));
	}

}
