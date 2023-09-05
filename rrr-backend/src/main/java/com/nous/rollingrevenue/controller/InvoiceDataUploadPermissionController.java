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
import com.nous.rollingrevenue.service.InvoiceDataUploadPermissionService;
import com.nous.rollingrevenue.vo.InvoiceDataUploadPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/invoice-data-upload-permission")
@CrossOrigin(origins = "*")
public class InvoiceDataUploadPermissionController {

	@Autowired
	private InvoiceDataUploadPermissionService invoiceDataUploadPermissionService;

	@Operation(summary = "Save InvoiceDataUploadPermission")
	@PostMapping
	public WSResponse<Boolean> saveInvoiceDataUploadPermission(
			@RequestBody @Valid InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO) {
		invoiceDataUploadPermissionService.saveInvoiceDataUploadPermission(invoiceDataUploadPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update InvoiceDataUploadPermission by invoiceDataUploadPermissionId")
	@PutMapping(path = "{invoiceDataUploadPermissionId}")
	public WSResponse<Boolean> updateInvoiceDataUploadPermissionById(@PathVariable Long invoiceDataUploadPermissionId,
			@RequestBody @Valid InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO) {
		invoiceDataUploadPermissionService.updateInvoiceDataUploadPermissionById(invoiceDataUploadPermissionId,
				invoiceDataUploadPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete InvoiceDataUploadPermission by invoiceDataUploadPermissionId")
	@DeleteMapping(path = "{invoiceDataUploadPermissionId}")
	public WSResponse<Boolean> deleteInvoiceDataUploadPermission(@PathVariable Long invoiceDataUploadPermissionId) {
		invoiceDataUploadPermissionService.deleteInvoiceDataUploadPermissionById(invoiceDataUploadPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get All InvoiceDataUploadPermission")
	@GetMapping
	public WSResponse<List<InvoiceDataUploadPermissionVO>> getAllInvoiceDataUploadPermission() {
		List<InvoiceDataUploadPermissionVO> invoiceDataUploadPermissions = invoiceDataUploadPermissionService
				.getInvoiceDataUploadPermissions();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, invoiceDataUploadPermissions);
	}

	@Operation(summary = "Get InvoiceDataUploadPermission by invoiceDataUploadPermissionId")
	@GetMapping(path = "{invoiceDataUploadPermissionId}")
	public WSResponse<InvoiceDataUploadPermissionVO> getInvoiceDataUploadPermissionById(
			@PathVariable Long invoiceDataUploadPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				invoiceDataUploadPermissionService.getInvoiceDataUploadPermissionById(invoiceDataUploadPermissionId));
	}

}
