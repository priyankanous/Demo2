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
import com.nous.rollingrevenue.service.AnnualTargetEntryService;
import com.nous.rollingrevenue.vo.AnnualTargetEntryVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/annual-target-entry")
@CrossOrigin(origins = "*")
public class AnnualTargetEntryController {
	
	@Autowired
	private AnnualTargetEntryService annualTargetEntryService;
	
	@Operation(summary = "Get All AnnualTargetEntry")
	@GetMapping
	public WSResponse<List<AnnualTargetEntryVO>> getAllAnnualTargetEntry() {
		List<AnnualTargetEntryVO> annualTargetEntryVOs = annualTargetEntryService.getAllAnnualTargetEntry();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, annualTargetEntryVOs);
	}

	@Operation(summary = "Save AnnualTargetEntry")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WSResponse<AnnualTargetEntryVO> saveAnnualTargetEntry(@RequestBody @Valid AnnualTargetEntryVO annualTargetEntryVO) {
		return WSResponse.buildWSResponse(HttpStatus.CREATED, RestMessage.SUCCESS, annualTargetEntryService.saveAnnualTargetEntry(annualTargetEntryVO));
	}

	@Operation(summary = "Get AnnualTargetEntry by Id")
	@GetMapping(path = "{annualTargetEntryId}")
	public WSResponse<AnnualTargetEntryVO> getAnnualTargetEntryById(@PathVariable Long annualTargetEntryId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, annualTargetEntryService.getAnnualTargetEntryById(annualTargetEntryId));
	}

	@Operation(summary = "Update AnnualTargetEntry by Id")
	@PutMapping(path = "{annualTargetEntryId}")
	public WSResponse<AnnualTargetEntryVO> updateAnnualTargetEntry(@PathVariable Long annualTargetEntryId, @RequestBody @Valid AnnualTargetEntryVO annualTargetEntryVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				annualTargetEntryService.updateAnnualTargetEntry(annualTargetEntryId, annualTargetEntryVO));
	}

	@Operation(summary = "Delete AnnualTargetEntry by Id")
	@DeleteMapping(path = "{annualTargetEntryId}")
	public WSResponse<String> deleteAnnualTargetEntry(@PathVariable Long annualTargetEntryId) {
		annualTargetEntryService.deleteAnnualTargetEntryById(annualTargetEntryId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
