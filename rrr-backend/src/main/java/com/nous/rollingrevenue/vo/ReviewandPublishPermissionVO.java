package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class ReviewandPublishPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long reviewandPublishPermissionId;

	@NotNull(message = "isReviewListViewRequired cannot be null or empty")
	private boolean isReviewListViewRequired;

	@NotNull(message = "isMarkPublishRequired cannot be null or empty")
	private boolean isMarkPublishRequired;

	private boolean isActive;

	public ReviewandPublishPermissionVO() {
	}

	public ReviewandPublishPermissionVO(Long reviewandPublishPermissionId, boolean isReviewListViewRequired,
			boolean isMarkPublishRequired, boolean isActive) {
		super();
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
		this.isReviewListViewRequired = isReviewListViewRequired;
		this.isMarkPublishRequired = isMarkPublishRequired;
		this.isActive = isActive;
	}

	public Long getReviewandPublishPermissionId() {
		return reviewandPublishPermissionId;
	}

	public void setReviewandPublishPermissionId(Long reviewandPublishPermissionId) {
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
	}

	public boolean getIsReviewListViewRequired() {
		return isReviewListViewRequired;
	}

	public void setReviewListViewRequired(boolean isReviewListViewRequired) {
		this.isReviewListViewRequired = isReviewListViewRequired;
	}

	public boolean getIsMarkPublishRequired() {
		return isMarkPublishRequired;
	}

	public void setMarkPublishRequired(boolean isMarkPublishRequired) {
		this.isMarkPublishRequired = isMarkPublishRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}