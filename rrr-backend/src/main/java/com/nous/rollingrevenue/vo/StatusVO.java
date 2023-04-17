package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class StatusVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long statusId;

	@NotBlank(message = "StatusName cannot be null or empty")
	private String statusName;

	@NotBlank(message = "StatusDisplayName cannot be null or empty")
	private String statusDisplayName;

	private boolean isActive;

	public StatusVO() {

	}

	public StatusVO(Long statusId, String statusName, String statusDisplayName, boolean isActive) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
		this.statusDisplayName = statusDisplayName;
		this.isActive = isActive;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusDisplayName() {
		return statusDisplayName;
	}

	public void setStatusDisplayName(String statusDisplayName) {
		this.statusDisplayName = statusDisplayName;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
