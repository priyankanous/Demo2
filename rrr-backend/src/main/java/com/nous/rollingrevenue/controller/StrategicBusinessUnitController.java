package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.nous.rollingrevenue.service.StrategicBusinessUnitService;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/sbu")
public class StrategicBusinessUnitController {
	
	@Autowired
	private StrategicBusinessUnitService sbuService;

	@Operation(summary = "Get All SBU")
	@GetMapping
	public WSResponse<List<StrategicBusinessUnitVO>> getAllSBU() {
		List<StrategicBusinessUnitVO> sbuVO = sbuService.getAllSBU();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, sbuVO);
	}
	
	@Operation(summary = "Save SBU")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WSResponse<StrategicBusinessUnitVO> saveSBU(@RequestBody @Valid StrategicBusinessUnitVO sbuVO) {
		return WSResponse.buildWSResponse(HttpStatus.CREATED, RestMessage.SUCCESS, sbuService.saveSBU(sbuVO));
	}
	
	@Operation(summary = "Get SBU by Id")
	@GetMapping(path = "{sbuId}")
	public WSResponse<StrategicBusinessUnitVO> getSBUById(@PathVariable Long sbuId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, sbuService.getSBUById(sbuId));
	}
	
	@Operation(summary = "Update SBU by Id")
	@PutMapping(path = "{sbuId}")
	public WSResponse<StrategicBusinessUnitVO> updateSBU(@PathVariable Long sbuId, @RequestBody @Valid StrategicBusinessUnitVO sbuVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				sbuService.updateSBU(sbuId, sbuVO));
	}
	
	@Operation(summary = "Delete SBU by Id")
	@DeleteMapping(path = "{sbuId}")
	public WSResponse<String> deleteSBU(@PathVariable Long sbuId) {
		sbuService.deleteSBUById(sbuId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
