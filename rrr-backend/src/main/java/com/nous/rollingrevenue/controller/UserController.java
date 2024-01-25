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
import com.nous.rollingrevenue.service.UserService;
import com.nous.rollingrevenue.vo.UserUpdateVO;
import com.nous.rollingrevenue.vo.UserVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Get All Users")
	@GetMapping
	public WSResponse<List<UserUpdateVO>> getAllUserEntity() {
		List<UserUpdateVO> usersEntity = userService.getAllUsersEntity();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, usersEntity);
	}

	@Operation(summary = "Save UserEntity")
	@PostMapping
	public WSResponse<String> saveUserEntity(@RequestBody @Valid UserVO userVO) {
		userService.saveUserEntity(userVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete UserEntity by EmployeeId")
	@DeleteMapping(path = "/user/{employeeId}")
	public WSResponse<String> deleteUserEntityByEmployeeId(@PathVariable String employeeId) {
		userService.deleteUserEntityByEmployeeId(employeeId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete UserEntity by UserId")
	@DeleteMapping(path = "/userEntity/{userId}")
	public WSResponse<String> deleteUserEntityByUserId(@PathVariable Long userId) {
		userService.deleteUserEntityByUserId(userId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get UserEntity by UserId")
	@GetMapping(path = "/{userId}")
	public WSResponse<UserUpdateVO> getUserEntityByUserId(@PathVariable Long userId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				userService.getUserEntityByUserId(userId));
	}

	@Operation(summary = "Get UserEntity by EmployeeId")
	@GetMapping(path = "/users/{employeeId}")
	public WSResponse<UserUpdateVO> getUserEntityByEmployeeId(@PathVariable String employeeId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				userService.getUserEntityByEmployeeId(employeeId));
	}

	@Operation(summary = "Update UserEntity by UserId")
	@PutMapping(path = "{userId}")
	public WSResponse<String> updateUserEntityByUserId(@PathVariable Long userId,
			@RequestBody @Valid UserUpdateVO userUpdateVO) {
		userService.updateUserEntityByUserId(userId, userUpdateVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}