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
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.BDMMeetingService;
import com.nous.rollingrevenue.vo.BDMMeetingVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdm-meeting")
@CrossOrigin(origins = "*")
public class BDMMeetingController {

	@Autowired
	private BDMMeetingService bdmMeetingService;

	@Operation(summary = "Save BDMMeeting")
	@PostMapping
	public WSResponse<BDMMeetingVO> saveBDMMeeting(@RequestBody @Valid BDMMeetingVO bdmAccountVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				bdmMeetingService.saveBDMMeeting(bdmAccountVO));
	}

	@Operation(summary = "Delete BDMMeeting by Id")
	@DeleteMapping(path = "{bdmMeetingId}")
	public WSResponse<String> deleteBDMMeeting(@PathVariable Long bdmMeetingId) {
		bdmMeetingService.deleteBDMMeetingById(bdmMeetingId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update BDMMeeting by Id")
	@PutMapping(path = "{bdmMeetingId}")
	public WSResponse<BDMMeetingVO> updateBDMMeeting(@PathVariable Long bdmMeetingId,
			@RequestBody @Valid BDMMeetingVO bdmMeetingVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				bdmMeetingService.updateBDMMeeting(bdmMeetingId, bdmMeetingVO));
	}

	@Operation(summary = "Get BDMMeetings By FinancialYear")
	@GetMapping(path = "/financial-year/{financialYear}")
	public WSResponse<List<BDMMeetingVO>> getBDMMeetingsByFinancialYear(@PathVariable String financialYear) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				bdmMeetingService.getBDMMeetingByFinancialYear(financialYear));
	}
	
	@Operation(summary = "Activate or Deactivate BDMMeeting by Id")
	@PutMapping(path = "/activate-or-deactivate/{bdmMeetingId}")
	public WSResponse<BDMMeetingVO> activateOrDeactivateBDMMeetingById(@PathVariable Long bdmMeetingId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				bdmMeetingService.activateOrDeactivateById(bdmMeetingId));
	}

}
