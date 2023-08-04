package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.FortnightlyMeetingService;
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
	@GetMapping(path = "/{financialYear}")
	public WSResponse<List<FortnightlyMeetingVO>> getFortnightlyMeetingsByFinancialYear(
			@PathVariable String financialYear) {
		List<FortnightlyMeetingVO> fortnightlyMeetingVOs = fortnightlyMeetingService
				.getFortnightlyMeetingsByFinancialYear(financialYear);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, fortnightlyMeetingVOs);
	}

	@Operation(summary = "Generate Fortnightly Meetings by FinancialYear")
	@PostMapping
	public WSResponse<String> genearteAndSaveFortnightlyMeetings(
			@RequestBody @Valid FortnightlyMeetingVO fortnightlyMeetingVO) {
		fortnightlyMeetingService.generateFortnightlyMeetings(fortnightlyMeetingVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Update Fortnightly")
	@PutMapping
	public WSResponse<String> updateFortnightlyMeetings(
			@RequestBody @Valid FortnightlyMeetingVO fortnightlyMeetingVO) {
		fortnightlyMeetingService.updateFortnightlyMeetings(fortnightlyMeetingVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Activate or Deactivate Fortnightly Meeting by Id")
	@PutMapping(path = "/activate-or-deactivate/{id}")
	public WSResponse<String> activateOrDeactivateFortnightlyMeetingById(@PathVariable Long id) {
		fortnightlyMeetingService.activateOrDeactivateById(id);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Get Fortnightly Meeting by Id")
	@GetMapping(path = "/meetingId/{meetingId}")
	public WSResponse<FortnightlyMeetingVO> getFortnightlyMeetingsById(@PathVariable Long meetingId) {
	return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
	fortnightlyMeetingService.getFortnightlyMeetingsById(meetingId));

	}

}
