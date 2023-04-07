package com.nous.rollingrevenue.controller;

import java.util.List;

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
import com.nous.rollingrevenue.service.FortnightlyMeetingService;
import com.nous.rollingrevenue.vo.FinancialYearVO;
import com.nous.rollingrevenue.vo.FortnightlyMeetingVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/fortnightly-meeting")
@CrossOrigin(origins = "*")
public class FortnightlyMeetingController {

	@Autowired
	private FortnightlyMeetingService fortnightlyMeetingService;

	@Operation(summary = "Get Fortnightly Meetings by FinancialYear")
	@GetMapping
	public WSResponse<List<FortnightlyMeetingVO>> getFortnightlyMeetingsByFinancialYear(
			@RequestParam String financialYear) {
		List<FortnightlyMeetingVO> fortnightlyMeetingVOs = fortnightlyMeetingService
				.getFortnightlyMeetingsByFinancialYear(financialYear);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, fortnightlyMeetingVOs);
	}

	@Operation(summary = "Generate Fortnightly Meetings by FinancialYear")
	@PostMapping
	public WSResponse<String> genearteAndSaveFortnightlyMeetingsByFinancialYear(
			@RequestBody @Valid FinancialYearVO financialYearVO) {
		fortnightlyMeetingService.generateFortnightlyMeetingsOfFinancialYear(financialYearVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete Fortnightly Meetings by FinancialYear")
	@DeleteMapping("{financialYear}")
	public WSResponse<String> deleteFortnightlyMeetingsByFinancialYear(@PathVariable String financialYear) {
		fortnightlyMeetingService.deleteFortnightlyMeetingByFinancialYear(financialYear);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Activate or Deactivate Fortnightly Meeting by Id")
	@PutMapping(path = "/activate-or-deactivate/{id}")
	public WSResponse<FortnightlyMeetingVO> activateOrDeactivateFortnightlyMeetingById(@PathVariable Long id) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				fortnightlyMeetingService.activateOrDeactivateById(id));
	}

}
