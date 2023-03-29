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
import com.nous.rollingrevenue.service.WorkOrderStatusService;
import com.nous.rollingrevenue.vo.WorkOrderStatusVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/wostatus")
@CrossOrigin(origins = "*")
public class WorkOrderStatusController {
	
	@Autowired
	private WorkOrderStatusService woStatusService;
	
	@Operation(summary = "Get All WorkOrderStatus")
	@GetMapping
	public WSResponse<List<WorkOrderStatusVO>> getAllWorkOrderStatus() {
		List<WorkOrderStatusVO> woStatusVOs = woStatusService.getAllWorkOrderStatus();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, woStatusVOs);
	}
	
	@Operation(summary = "Save WorkOrderStatus")
	@PostMapping
	public WSResponse<WorkOrderStatusVO> saveWorkOrderStatus(@RequestBody @Valid WorkOrderStatusVO woStatusVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, woStatusService.saveWorkOrderStatus(woStatusVO));
	}
	
	@Operation(summary = "Get WorkOrderStatus by Id")
	@GetMapping(path = "{woStatusId}")
	public WSResponse<WorkOrderStatusVO> getWorkOrderStatusById(@PathVariable Long woStatusId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, woStatusService.getWorkOrderStatusById(woStatusId));
	}
	
	@Operation(summary = "Update WorkOrderStatus by Id")
	@PutMapping(path = "{woStatusId}")
	public WSResponse<WorkOrderStatusVO> updateWorkOrderStatus(@PathVariable Long woStatusId, @RequestBody @Valid WorkOrderStatusVO woStatusVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				woStatusService.updateWorkOrderStatus(woStatusId, woStatusVO));
	}
	
	@Operation(summary = "Delete WorkOrderStatus by Id")
	@DeleteMapping(path = "{woStatusId}")
	public WSResponse<String> deleteWorkOrderStatus(@PathVariable Long woStatusId) {
		woStatusService.deleteWorkOrderStatusById(woStatusId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
