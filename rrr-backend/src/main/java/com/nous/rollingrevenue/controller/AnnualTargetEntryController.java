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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.exception.InvalidFileTypeException;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.helper.ExcelHelper;
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
	public WSResponse<AnnualTargetEntryVO> saveAnnualTargetEntry(
			@RequestBody @Valid AnnualTargetEntryVO annualTargetEntryVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				annualTargetEntryService.saveAnnualTargetEntry(annualTargetEntryVO));
	}

	@Operation(summary = "Get AnnualTargetEntry by Id")
	@GetMapping(path = "{annualTargetEntryId}")
	public WSResponse<AnnualTargetEntryVO> getAnnualTargetEntryById(@PathVariable Long annualTargetEntryId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				annualTargetEntryService.getAnnualTargetEntryById(annualTargetEntryId));
	}

	@Operation(summary = "Update AnnualTargetEntry by Id")
	@PutMapping(path = "{annualTargetEntryId}")
	public WSResponse<AnnualTargetEntryVO> updateAnnualTargetEntry(@PathVariable Long annualTargetEntryId,
			@RequestBody @Valid AnnualTargetEntryVO annualTargetEntryVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				annualTargetEntryService.updateAnnualTargetEntry(annualTargetEntryId, annualTargetEntryVO));
	}

	@Operation(summary = "Delete AnnualTargetEntry by Id")
	@DeleteMapping(path = "{annualTargetEntryId}")
	public WSResponse<String> deleteAnnualTargetEntry(@PathVariable Long annualTargetEntryId) {
		annualTargetEntryService.deleteAnnualTargetEntryById(annualTargetEntryId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Save AnnualTargetEntries from Uploaded Excel File")
	@PostMapping("/upload")
	public WSResponse<String> uploadExcelDataOfAnnualTargetEntry(@RequestParam("file") MultipartFile file,
			@RequestParam("financialYear") String financialYear) {
		if (file.isEmpty()) {
			throw new RecordNotFoundException(ErrorConstants.INPUT_FILE_MISSING);
		}
		if (!ExcelHelper.checkExcelFormat(file)) {
			throw new InvalidFileTypeException(ErrorConstants.INVALID_INPUT_FILE);
		}
		annualTargetEntryService.saveExcelDataOfAnnualTargetEntry(file, financialYear);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get AnnualTargetEntry By Pagination")
	@GetMapping("/page")
	public WSResponse<List<AnnualTargetEntryVO>> getAnnualTargetEntryByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "annualTargetEntryId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, annualTargetEntryService.getPagination(pagenumber, pagesize, sortBy));
	}
}
