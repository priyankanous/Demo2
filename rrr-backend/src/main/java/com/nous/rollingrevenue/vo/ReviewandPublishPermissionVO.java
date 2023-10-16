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

	private boolean viewRequired;

	private boolean reviewAndSubmitRequired;

	private boolean saveRequired;

	private boolean copyRequired;

	private boolean editRequired;

	private boolean publishRequired;

	private boolean saveRecipientsRequired;

	private boolean isActive;

	public ReviewandPublishPermissionVO() {
	}

	public ReviewandPublishPermissionVO(Long reviewandPublishPermissionId, boolean reviewandPublishPermissionAll,
			boolean viewRequired, boolean reviewAndSubmitRequired, boolean saveRequired, boolean copyRequired,
			boolean editRequired, boolean publishRequired, boolean saveRecipientsRequired, boolean isActive) {
		super();
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
		this.reviewandPublishPermissionAll = reviewandPublishPermissionAll;
		this.viewRequired = viewRequired;
		this.reviewAndSubmitRequired = reviewAndSubmitRequired;
		this.saveRequired = saveRequired;
		this.copyRequired = copyRequired;
		this.editRequired = editRequired;
		this.publishRequired = publishRequired;
		this.saveRecipientsRequired = saveRecipientsRequired;
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
		return viewRequired;
	}

	public void setViewRequired(boolean viewRequired) {
		this.viewRequired = viewRequired;
	}

	public boolean isReviewAndSubmitRequired() {
		return reviewAndSubmitRequired;
	}

	public void setReviewAndSubmitRequired(boolean reviewAndSubmitRequired) {
		this.reviewAndSubmitRequired = reviewAndSubmitRequired;
	}

	public boolean isSaveRequired() {
		return saveRequired;
	}

	public void setSaveRequired(boolean saveRequired) {
		this.saveRequired = saveRequired;
	}

	public boolean isCopyRequired() {
		return copyRequired;
	}

	public void setCopyRequired(boolean copyRequired) {
		this.copyRequired = copyRequired;
	}

	public boolean isEditRequired() {
		return editRequired;
	}

	public void setEditRequired(boolean editRequired) {
		this.editRequired = editRequired;
	}

	public boolean isPublishRequired() {
		return publishRequired;
	}

	public void setPublishRequired(boolean publishRequired) {
		this.publishRequired = publishRequired;
	}

	public boolean isSaveRecipientsRequired() {
		return saveRecipientsRequired;
	}

	public void setSaveRecipientsRequired(boolean saveRecipientsRequired) {
		this.saveRecipientsRequired = saveRecipientsRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}