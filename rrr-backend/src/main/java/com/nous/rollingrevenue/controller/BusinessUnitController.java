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
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.convertor.BusinessUnitVOToBusinessUnit;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.service.BusinessUnitService;
import com.nous.rollingrevenue.vo.BusinessUnitVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class BusinessUnitController {

	@Autowired
	BusinessUnitService businessUnitService;

	@Operation(summary = "save business unit")
	@PostMapping(value = "/businessunits")
	public WSResponse<BusinessUnitVO> saveBusinessUnit(@RequestBody @Valid BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = BusinessUnitVOToBusinessUnit.convertBusinessUnitVOToBusinessUnit(businessUnitVO);
		businessUnit = businessUnitService.addBusinessUnit(businessUnit);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				BusinessUnitVOToBusinessUnit.convertBusinessUnitToBusinessUnitVO(businessUnit));
	}

	@Operation(summary = "Update business unit")
	@PutMapping(value = "/businessunits/{id}")
	public WSResponse<BusinessUnitVO> updateBusinessUnit(@PathVariable @Valid Long id,
			@RequestBody @Valid BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = businessUnitService.updateBusinessUnit(id, businessUnitVO);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				BusinessUnitVOToBusinessUnit.convertBusinessUnitToBusinessUnitVO(businessUnit));

	}

	@Operation(summary = "Get business unit by Id")
	@GetMapping(value = "/businessunits/{id}")
	public WSResponse<BusinessUnitVO> getBusinessUnit(@PathVariable @Valid Long id) {
		BusinessUnit businessUnit = null;
		businessUnit = businessUnitService.getBusinessUnit(id);
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				BusinessUnitVOToBusinessUnit.convertBusinessUnitToBusinessUnitVO(businessUnit));
	}

	@Operation(summary = "Remove business unit")
	@DeleteMapping(value = "/businessunits/{id}")
	public WSResponse<String> removeEmployee(@PathVariable @Valid Long id) {
		businessUnitService.deleteBusinessUnit(id);
		String data = "Business Unit id  " + id + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get Business Units")
	@GetMapping(value = "/businessunits")
	public WSResponse<List<BusinessUnitVO>> getEmployees() {
		List<BusinessUnitVO> businessUnits = new ArrayList<>();
		businessUnitService.getBusinessUnits().stream().forEach(e -> {
			businessUnits.add(BusinessUnitVOToBusinessUnit.convertBusinessUnitToBusinessUnitVO(e));

		});
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, businessUnits);
	}

}
