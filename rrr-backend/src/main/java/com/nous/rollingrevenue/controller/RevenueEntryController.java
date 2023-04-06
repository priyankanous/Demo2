package com.nous.rollingrevenue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.RevenueEntryService;
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

}
