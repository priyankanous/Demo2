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
import com.nous.rollingrevenue.service.ReviewandPublishPermissionService;
import com.nous.rollingrevenue.vo.ReviewandPublishPermissionVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/review-and-publish-permiision")
@CrossOrigin(origins = "*")
public class ReviewAndPublishPermissionController {

	@Autowired
	private ReviewandPublishPermissionService reviewandPublishPermissionService;

	@Operation(summary = "Get All ReviewandPublishPermission")
	@GetMapping
	public WSResponse<List<ReviewandPublishPermissionVO>> getAllReviewandPublishPermission() {
		List<ReviewandPublishPermissionVO> reviewandPublishPermission = reviewandPublishPermissionService
				.getAllReviewandPublishPermission();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, reviewandPublishPermission);
	}

	@Operation(summary = "Save ReviewandPublishPermission")
	@PostMapping
	public WSResponse<String> saveReviewandPublishPermission(
			@RequestBody @Valid ReviewandPublishPermissionVO reviewandPublishPermissionVO) {
		reviewandPublishPermissionService.saveReviewandPublishPermission(reviewandPublishPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Get ReviewandPublishPermission by reviewandPublishPermissionId")
	@GetMapping(path = "{reviewandPublishPermissionId}")
	public WSResponse<ReviewandPublishPermissionVO> getReviewandPublishPermissionById(
			@PathVariable Long reviewandPublishPermissionId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				reviewandPublishPermissionService.getReviewandPublishPermissionById(reviewandPublishPermissionId));
	}

	@Operation(summary = "Update ReviewandPublishPermission by reviewandPublishPermissionId")
	@PutMapping(path = "{reviewandPublishPermissionId}")
	public WSResponse<String> updateReviewandPublishPermission(@PathVariable Long reviewandPublishPermissionId,
			@RequestBody @Valid ReviewandPublishPermissionVO reviewandPublishPermissionVO) {
		reviewandPublishPermissionService.updateReviewandPublishPermission(reviewandPublishPermissionId,
				reviewandPublishPermissionVO);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

	@Operation(summary = "Delete ReviewandPublishPermissionService by Id")
	@DeleteMapping(path = "{reviewandPublishPermissionId}")
	public WSResponse<String> deleteReviewandPublishPermission(@PathVariable Long reviewandPublishPermissionId) {
		reviewandPublishPermissionService.deleteReviewandPublishPermissionById(reviewandPublishPermissionId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}

}
