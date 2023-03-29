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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.StatusService;
import com.nous.rollingrevenue.vo.StatusVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/status")
@CrossOrigin(origins = "*")
public class StatusController {
	
	@Autowired
	private StatusService statusService;
	
	@Operation(summary = "Get All Status")
	@GetMapping
	public WSResponse<List<StatusVO>> getAllStatus() {
		List<StatusVO> statusVOs = statusService.getAllStatus();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, statusVOs);
	}
	
	@Operation(summary = "Save Status")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WSResponse<StatusVO> saveStatus(@RequestBody @Valid StatusVO statusVO) {
		return WSResponse.buildWSResponse(HttpStatus.CREATED, RestMessage.SUCCESS, statusService.saveStatus(statusVO));
	}
	
	@Operation(summary = "Get Status by Id")
	@GetMapping(path = "{statusId}")
	public WSResponse<StatusVO> getStatusById(@PathVariable Long statusId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, statusService.getStatusById(statusId));
	}
	
	@Operation(summary = "Update Status by Id")
	@PutMapping(path = "{statusId}")
	public WSResponse<StatusVO> updateStatus(@PathVariable Long statusId, @RequestBody @Valid StatusVO statusVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				statusService.updateStatus(statusId, statusVO));
	}
	
	@Operation(summary = "Delete Status by Id")
	@DeleteMapping(path = "{statusId}")
	public WSResponse<String> deleteStatus(@PathVariable Long statusId) {
		statusService.deleteStatusById(statusId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
