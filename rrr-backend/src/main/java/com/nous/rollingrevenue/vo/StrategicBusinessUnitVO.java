package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class StrategicBusinessUnitVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long sbuId;

	@NotBlank(message = "SBUName cannot be null or empty")
	private String sbuName;

	@NotBlank(message = "SBUDisplayName cannot be null or empty")
	private String sbuDisplayName;

	@NotBlank(message = "BUDisplayName cannot be null or empty")
	private String buDisplayName;

	private boolean isActive;

	public StrategicBusinessUnitVO() {

	}

	public StrategicBusinessUnitVO(Long sbuId, String sbuName, String sbuDisplayName, String buDisplayName,
			boolean isActive) {
		super();
		this.sbuId = sbuId;
		this.sbuName = sbuName;
		this.sbuDisplayName = sbuDisplayName;
		this.buDisplayName = buDisplayName;
		this.isActive = isActive;
	}

	public Long getSbuId() {
		return sbuId;
	}

	public void setSbuId(Long sbuId) {
		this.sbuId = sbuId;
	}

	public String getSbuName() {
		return sbuName;
	}

	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
	}

	public String getSbuDisplayName() {
		return sbuDisplayName;
	}

	public void setSbuDisplayName(String sbuDisplayName) {
		this.sbuDisplayName = sbuDisplayName;
	}

	public String getBuDisplayName() {
		return buDisplayName;
	}

	public void setBuDisplayName(String buDisplayName) {
		this.buDisplayName = buDisplayName;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "StrategicBusinessUnitVO [sbuId=" + sbuId + ", sbuName=" + sbuName + ", sbuDisplayName=" + sbuDisplayName
				+ ", buDisplayName=" + buDisplayName + ", isActive=" + isActive + "]";
	}

}
