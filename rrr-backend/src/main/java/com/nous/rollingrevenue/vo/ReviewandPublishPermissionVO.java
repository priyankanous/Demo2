package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewandPublishPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long reviewandPublishPermissionId;

	private boolean reviewandPublishPermissionAll;

	private boolean isViewRequired;

	private boolean isReviewAndSubmitRequired;

	private boolean isSaveRequired;

	private boolean isCopyRequired;

	private boolean isEditRequired;

	private boolean isPublishRequired;

	private boolean isSaveRecipientsRequired;

	private boolean isActive;

	public ReviewandPublishPermissionVO() {
	}

	public ReviewandPublishPermissionVO(Long reviewandPublishPermissionId, boolean reviewandPublishPermissionAll,
			boolean isViewRequired, boolean isReviewAndSubmitRequired, boolean isSaveRequired, boolean isCopyRequired,
			boolean isEditRequired, boolean isPublishRequired, boolean isSaveRecipientsRequired, boolean isActive) {
		super();
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
		this.reviewandPublishPermissionAll = reviewandPublishPermissionAll;
		this.isViewRequired = isViewRequired;
		this.isReviewAndSubmitRequired = isReviewAndSubmitRequired;
		this.isSaveRequired = isSaveRequired;
		this.isCopyRequired = isCopyRequired;
		this.isEditRequired = isEditRequired;
		this.isPublishRequired = isPublishRequired;
		this.isSaveRecipientsRequired = isSaveRecipientsRequired;
		this.isActive = isActive;
	}

	public Long getReviewandPublishPermissionId() {
		return reviewandPublishPermissionId;
	}

	public void setReviewandPublishPermissionId(Long reviewandPublishPermissionId) {
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
	}

	public boolean isReviewandPublishPermissionAll() {
		return reviewandPublishPermissionAll;
	}

	public void setReviewandPublishPermissionAll(boolean reviewandPublishPermissionAll) {
		this.reviewandPublishPermissionAll = reviewandPublishPermissionAll;
	}

	public boolean isViewRequired() {
		return isViewRequired;
	}

	public void setViewRequired(boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public boolean isReviewAndSubmitRequired() {
		return isReviewAndSubmitRequired;
	}

	public void setReviewAndSubmitRequired(boolean isReviewAndSubmitRequired) {
		this.isReviewAndSubmitRequired = isReviewAndSubmitRequired;
	}

	public boolean isSaveRequired() {
		return isSaveRequired;
	}

	public void setSaveRequired(boolean isSaveRequired) {
		this.isSaveRequired = isSaveRequired;
	}

	public boolean isCopyRequired() {
		return isCopyRequired;
	}

	public void setCopyRequired(boolean isCopyRequired) {
		this.isCopyRequired = isCopyRequired;
	}

	public boolean isEditRequired() {
		return isEditRequired;
	}

	public void setEditRequired(boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public boolean isPublishRequired() {
		return isPublishRequired;
	}

	public void setPublishRequired(boolean isPublishRequired) {
		this.isPublishRequired = isPublishRequired;
	}

	public boolean isSaveRecipientsRequired() {
		return isSaveRecipientsRequired;
	}

	public void setSaveRecipientsRequired(boolean isSaveRecipientsRequired) {
		this.isSaveRecipientsRequired = isSaveRecipientsRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}