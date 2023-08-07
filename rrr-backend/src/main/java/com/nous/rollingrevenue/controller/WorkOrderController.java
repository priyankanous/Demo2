package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.nous.rollingrevenue.service.WorkOrderService;
import com.nous.rollingrevenue.vo.WorkOrderVO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/work-order")
@CrossOrigin(origins = "*")
public class WorkOrderController {

	@Autowired
	private WorkOrderService workOrderService;

	@Operation(summary = "Save WorkOrderEntries from Uploaded Excel File")
	@PostMapping("/upload")
	public WSResponse<String> uploadExcelDataOfAnnualTargetEntry(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			throw new RecordNotFoundException(ErrorConstants.INPUT_FILE_MISSING);
		}
		if (!ExcelHelper.checkExcelFormat(file)) {
			throw new InvalidFileTypeException(ErrorConstants.INVALID_INPUT_FILE);
		}
		workOrderService.saveExcelDataOfWorkOrder(file);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Activate or Deactivate WorkOrder by WorkOrderId")
	@PutMapping(path = "/activate-or-deactivate/{workOrderId}")
	public WSResponse<String> activateOrDeactivateWorkOrderById(@PathVariable Long workOrderId) {
		workOrderService.activateOrDeactivateWorkOrderById(workOrderId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get Work Order by Account Name")
	@GetMapping(path = "/account-name/{accountName}")
	public WSResponse<List<WorkOrderVO>> getWorkOrderByAccountName(@PathVariable String accountName) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, workOrderService.getWorkOrderByAccountName(accountName));
	}

	@Operation(summary = "Get All Work Orders")
	@GetMapping
	public WSResponse<List<WorkOrderVO>> getAllWorkOrders() {
		List<WorkOrderVO> workOrderVOs = workOrderService.getAllWorkOrders();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, workOrderVOs);
	}

	@Operation(summary = "Get Work Orders By Pagination")
	@GetMapping("/page")
	public WSResponse<List<WorkOrderVO>> getWorkOrdersByPagination(@RequestParam(defaultValue = "1") int pagenumber,
			@RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "workOrderId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS,
				workOrderService.getWorkOrdersByPagination(pagenumber, pagesize, sortBy));
	}

}
