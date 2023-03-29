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
import com.nous.rollingrevenue.service.StrategicBusinessUnitHeadService;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitHeadVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/sbuhead")
@CrossOrigin(origins = "*")
public class StrategicBusinessUnitHeadController {
	
	@Autowired
	private StrategicBusinessUnitHeadService sbuHeadService;

	@Operation(summary = "Get All SBUHead")
	@GetMapping
	public WSResponse<List<StrategicBusinessUnitHeadVO>> getAllSBUHead() {
		List<StrategicBusinessUnitHeadVO> sbuHeadVO = sbuHeadService.getAllSBUHead();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, sbuHeadVO);
	}
	
	@Operation(summary = "Save SBUHead")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WSResponse<StrategicBusinessUnitHeadVO> saveSBUHead(@RequestBody @Valid StrategicBusinessUnitHeadVO sbuHeadVO) {
		return WSResponse.buildWSResponse(HttpStatus.CREATED, RestMessage.SUCCESS, sbuHeadService.saveSBUHead(sbuHeadVO));
	}
	
	@Operation(summary = "Get SBUHead by Id")
	@GetMapping(path = "{sbuHeadId}")
	public WSResponse<StrategicBusinessUnitHeadVO> getSBUHeadById(@PathVariable Long sbuHeadId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, sbuHeadService.getSBUHeadById(sbuHeadId));
	}
	
	@Operation(summary = "Update SBUHead by Id")
	@PutMapping(path = "{sbuHeadId}")
	public WSResponse<StrategicBusinessUnitHeadVO> updateSBUHead(@PathVariable Long sbuHeadId, @RequestBody @Valid StrategicBusinessUnitHeadVO sbuHeadVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				sbuHeadService.updateSBUHead(sbuHeadId, sbuHeadVO));
	}
	
	@Operation(summary = "Delete SBUHead by Id")
	@DeleteMapping(path = "{sbuHeadId}")
	public WSResponse<String> deleteSBUHead(@PathVariable Long sbuHeadId) {
		sbuHeadService.deleteSBUHeadById(sbuHeadId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}


}
