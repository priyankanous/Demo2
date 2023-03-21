package com.nous.rollingrevenue.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.nous.rollingrevenue.convertor.BDMVOToBDM;
import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.service.BusinessDevelopmentManagerService;
import com.nous.rollingrevenue.vo.BDMVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdm")
public class BusinessDevelopmentManagerController {

	@Autowired
	BusinessDevelopmentManagerService businessDevelopmentManagerService;

	@Operation(summary = "save BDM")
	@PostMapping
	public WSResponse<BDMVO> saveBDM(@RequestBody @Valid BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = BDMVOToBDM.convertBdmVOToBdm(bdmVO);
		bdm = businessDevelopmentManagerService.addBDMDetails(bdm);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, BDMVOToBDM.convertBdmToBdmVO(bdm));
	}

	@Operation(summary = "Update BDM")
	@PutMapping(path = "{bdmId}")
	public WSResponse<BDMVO> updateBDM(@PathVariable @Valid Long bdmId, @RequestBody @Valid BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = businessDevelopmentManagerService.updateBDMDetails(bdmId, bdmVO);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, BDMVOToBDM.convertBdmToBdmVO(bdm));
	}

	@Operation(summary = "Get BDM by Id")
	@GetMapping(path = "{bdmId}")
	public WSResponse<BDMVO> getBdmById(@PathVariable @Valid Long bdmId) {
		BusinessDevelopmentManager bdm = null;
		bdm = businessDevelopmentManagerService.getBdmById(bdmId);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, BDMVOToBDM.convertBdmToBdmVO(bdm));
	}

	@Operation(summary = "Delete BDM by Id")
	@DeleteMapping(path = "{bdmId}")
	public WSResponse<String> removeBDM(@PathVariable @Valid Long bdmId) {
		businessDevelopmentManagerService.deleteBDM(bdmId);
		String data = "Business Development Manager id  " + bdmId + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get All BDM")
	@GetMapping
	public WSResponse<List<BDMVO>> getBDMDetails() {
		List<BDMVO> businessUnits = new ArrayList<>();
		businessDevelopmentManagerService.getBDM().stream().forEach(e -> {
			businessUnits.add(BDMVOToBDM.convertBdmToBdmVO(e));
		});
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, businessUnits);
	}

}
