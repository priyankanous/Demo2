package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class WorkOrderStatusVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long woStatusId;

	@NotBlank(message = "WOStatusName cannot be null or empty")
	private String woStatusName;

	@NotBlank(message = "WOStatusDisplayName cannot be null or empty")
	private String woStatusDisplayName;

	private boolean isActive;

	public WorkOrderStatusVO() {

	}

	public WorkOrderStatusVO(Long woStatusId, String woStatusName, String woStatusDisplayName, boolean isActive) {
		super();
		this.woStatusId = woStatusId;
		this.woStatusName = woStatusName;
		this.woStatusDisplayName = woStatusDisplayName;
		this.isActive = isActive;
	}

	public Long getWoStatusId() {
		return woStatusId;
	}

	public void setWoStatusId(Long woStatusId) {
		this.woStatusId = woStatusId;
	}

	public String getWoStatusName() {
		return woStatusName;
	}

	public void setWoStatusName(String woStatusName) {
		this.woStatusName = woStatusName;
	}

	public String getWoStatusDisplayName() {
		return woStatusDisplayName;
	}

	public void setWoStatusDisplayName(String woStatusDisplayName) {
		this.woStatusDisplayName = woStatusDisplayName;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

}
