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
//import com.nous.rollingrevenue.service.NotificationConfigurationPermissionService;
//import com.nous.rollingrevenue.vo.NotificationConfigurationPermissionVO;
//
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/v1/notification-configuration-permission")
//@CrossOrigin(origins = "*")
//public class NotificationConfigurationPermissionController {
//
//	@Autowired
//	private NotificationConfigurationPermissionService notificationConfigurationPermissionService;
//
//	@Operation(summary = "Save NotificationConfigurationPermission")
//	@PostMapping
//	public WSResponse<String> saveNotificationConfigurationPermission(
//			@RequestBody @Valid NotificationConfigurationPermissionVO notificationConfigurationPermissionVO) {
//		notificationConfigurationPermissionService
//				.saveNotificationConfigurationPermission(notificationConfigurationPermissionVO);
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
//	}
//
//	@Operation(summary = "Update NotificationConfigurationPermission by NotificationConfigurationPermissionId")
//	@PutMapping(path = "{notificationConfigurationPermissionId}")
//	public WSResponse<String> updateNotificationConfigurationPermissionById(
//			@PathVariable Long notificationConfigurationPermissionId,
//			@RequestBody @Valid NotificationConfigurationPermissionVO notificationConfigurationPermissionVO) {
//		notificationConfigurationPermissionService.updateNotificationConfigurationPermissionById(
//				notificationConfigurationPermissionId, notificationConfigurationPermissionVO);
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
//	}
//
//	@Operation(summary = "Delete NotificationConfigurationPermission by notificationConfigurationPermissionId")
//	@DeleteMapping(path = "{notificationConfigurationPermissionId}")
//	public WSResponse<String> deleteNotificationConfigurationPermission(
//			@PathVariable Long notificationConfigurationPermissionId) {
//		notificationConfigurationPermissionService
//				.deleteNotificationConfigurationPermissionById(notificationConfigurationPermissionId);
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
//	}
//
//	@Operation(summary = "Get All NotificationConfigurationPermission")
//	@GetMapping
//	public WSResponse<List<NotificationConfigurationPermissionVO>> getAllNotificationConfigurationPermission() {
//		List<NotificationConfigurationPermissionVO> notificationConfigurationPermissions = notificationConfigurationPermissionService
//				.getNotificationConfigurationPermissions();
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, notificationConfigurationPermissions);
//	}
//
//	@Operation(summary = "Get NotificationConfigurationPermission by notificationConfigurationPermissionId")
//	@GetMapping(path = "{notificationConfigurationPermissionId}")
//	public WSResponse<NotificationConfigurationPermissionVO> getNotificationConfigurationPermissionById(
//			@PathVariable Long notificationConfigurationPermissionId) {
//		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, notificationConfigurationPermissionService
//				.getNotificationConfigurationPermissionById(notificationConfigurationPermissionId));
//	}
//
//}
