package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.RevenueEntryService;
import com.nous.rollingrevenue.vo.ResourcesEntryVO;
import com.nous.rollingrevenue.vo.RollingRevenueAccountVO;
import com.nous.rollingrevenue.vo.RollingRevenueDetailsVO;
import com.nous.rollingrevenue.vo.RollingRevenueOpportunityVO;
import com.nous.rollingrevenue.vo.RollingRevenueVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/revenue")
@CrossOrigin(origins = "*")
public class RevenueEntryController {

	@Autowired
	private RevenueEntryService revenueEntryService;

	@Operation(summary = "Save Rolling Revenue Details")
	@PostMapping
	public WSResponse<String> saveRollingRevenue(@RequestBody @Valid RollingRevenueVO rollingRevenueVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				revenueEntryService.saveRollingRevenue(rollingRevenueVO));
	}

	@Operation(summary = "Get rolling revenue details by account level")
	@GetMapping(path = "/account/{id}")
	public WSResponse<RollingRevenueAccountVO> getRollingRevenueByAccountLevel(@PathVariable @Valid Long id) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, revenueEntryService.getRevenueByAccountLevel(id));
	}

	@Operation(summary = "Get rolling revenue details by opportunity level")
	@GetMapping(path = "/opportunity/{id}")
	public WSResponse<RollingRevenueOpportunityVO> getRollingRevenueByOpportunityLevel(@PathVariable @Valid Long id) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, revenueEntryService.getRevenueByOpportunityLevel(id));
	}

	@Operation(summary = "Get rolling revenue details by resource level")
	@GetMapping(path = "/resource/{id}")
	public WSResponse<ResourcesEntryVO> getRollingRevenueByResourceLevel(@PathVariable @Valid Long id) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, revenueEntryService.getRevenueByResourceLevel(id));
	}

	@Operation(summary = "Get rolling revenue details list")
	@GetMapping
	public WSResponse<List<RollingRevenueDetailsVO>> getRollingRevenueDetailsList() {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, revenueEntryService.getRollingRevenueDetailsList());
	}

}
