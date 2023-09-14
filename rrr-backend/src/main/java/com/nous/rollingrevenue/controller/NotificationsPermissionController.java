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
import com.nous.rollingrevenue.service.NotificationsPermissionService;
import com.nous.rollingrevenue.vo.NotificationsPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/notifications-permission")
@CrossOrigin(origins = "*")
public class NotificationsPermissionController {

	@Autowired
	private NotificationsPermissionService notificationsPermissionService;

	@Operation(summary = "Save NotificationsPermission")
	@PostMapping
	public WSResponse<String> saveNotificationsPermission(
			@RequestBody @Valid NotificationsPermissionVO notificationsPermissionVO) {
		notificationsPermissionService.saveNotificationsPermission(notificationsPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Update NotificationsPermission by NotificationsPermissionId")
	@PutMapping(path = "{notificationsPermissionId}")
	public WSResponse<String> updateNotificationsPermissionById(@PathVariable Long notificationsPermissionId,
			@RequestBody @Valid NotificationsPermissionVO notificationsPermissionVO) {
		notificationsPermissionService.updateNotificationsPermissionById(notificationsPermissionId,
				notificationsPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete NotificationsPermission by notificationsPermissionId")
	@DeleteMapping(path = "{notificationsPermissionId}")
	public WSResponse<String> deleteNotificationsPermission(@PathVariable Long notificationsPermissionId) {
		notificationsPermissionService.deleteNotificationsPermissionById(notificationsPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get All NotificationsPermission")
	@GetMapping
	public WSResponse<List<NotificationsPermissionVO>> getAllNotificationsPermission() {
		List<NotificationsPermissionVO> notificationsPermissions = notificationsPermissionService
				.getNotificationsPermissions();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, notificationsPermissions);
	}

	@Operation(summary = "Get NotificationsPermission by notificationsPermissionId")
	@GetMapping(path = "{notificationsPermissionId}")
	public WSResponse<NotificationsPermissionVO> getNotificationsPermissionById(
			@PathVariable Long notificationsPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				notificationsPermissionService.getNotificationsPermissionById(notificationsPermissionId));
	}
}
