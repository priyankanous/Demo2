package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
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

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}