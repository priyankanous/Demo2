//package com.nous.rollingrevenue.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nous.rollingrevenue.common.rest.RestMessage;
//import com.nous.rollingrevenue.common.rest.WSResponse;
//import com.nous.rollingrevenue.service.DashboardPermissionService;
//import com.nous.rollingrevenue.vo.DashboardPermissionVO;
//
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/v1/dashboard-permission")
//@CrossOrigin(origins = "*")
//public class DashboardPermissionController {
//
//	@Autowired
//	private DashboardPermissionService dashboardPermissionService;
//
//	@Operation(summary = "Get All DashboardPermission")
//	@GetMapping
//	public WSResponse<List<DashboardPermissionVO>> getAllDashboardPermission() {
//		List<DashboardPermissionVO> dashboardPermission = dashboardPermissionService.getAllDashboardPermission();
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, dashboardPermission);
//	}
//
//	@Operation(summary = "Save DashboardPermission")
//	@PostMapping
//	public WSResponse<String> saveDashboardPermission(@RequestBody @Valid DashboardPermissionVO dashboardPermissionVO) {
//		dashboardPermissionService.saveDashboardPermission(dashboardPermissionVO);
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
//	}
//
//	@Operation(summary = "Get DashboardPermission by dashboardPermissionId")
//	@GetMapping(path = "{dashboardPermissionId}")
//	public WSResponse<DashboardPermissionVO> getDashboardPermissionById(@PathVariable Long dashboardPermissionId) {
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
//				dashboardPermissionService.getDashboardPermissionById(dashboardPermissionId));
//	}
//
//	@Operation(summary = "Update DashboardPermission by dashboardPermissionId")
//	@PutMapping(path = "{dashboardPermissionId}")
//	public WSResponse<String> updatedashboardPermission(@PathVariable Long dashboardPermissionId,
//			@RequestBody @Valid DashboardPermissionVO dashboardPermissionVO) {
//		dashboardPermissionService.updateDashboardPermission(dashboardPermissionId, dashboardPermissionVO);
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
//	}
//
//	@Operation(summary = "Delete DashboardPermissionService by Id")
//	@DeleteMapping(path = "{dashboardPermissionId}")
//	public WSResponse<String> deleteDashboardPermission(@PathVariable Long dashboardPermissionId) {
//		dashboardPermissionService.deleteDashboardPermissionById(dashboardPermissionId);
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
//	}
//
//}
