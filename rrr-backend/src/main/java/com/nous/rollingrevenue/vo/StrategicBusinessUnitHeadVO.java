package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class StrategicBusinessUnitHeadVO implements Serializable {
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long sbuHeadId;
	
	@NotBlank(message = "SBUHeadName cannot be null or empty")
	private String sbuHeadName;

	@NotBlank(message = "SBUHeadDisplayName cannot be null or empty")
	private String sbuHeadDisplayName;

	@NotBlank(message = "SBUName cannot be null or empty")
	private String sbuName;

	private LocalDate activeFrom;

	private LocalDate activeUntil;

	public StrategicBusinessUnitHeadVO() {

	}

	public StrategicBusinessUnitHeadVO(Long sbuHeadId, String sbuHeadName, String sbuHeadDisplayName, String sbuName,
			LocalDate activeFrom, LocalDate activeUntil) {
		super();
		this.sbuHeadId = sbuHeadId;
		this.sbuHeadName = sbuHeadName;
		this.sbuHeadDisplayName = sbuHeadDisplayName;
		this.sbuName = sbuName;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
	}

	public Long getSbuHeadId() {
		return sbuHeadId;
	}

	public void setSbuHeadId(Long sbuHeadId) {
		this.sbuHeadId = sbuHeadId;
	}

	public String getSbuHeadName() {
		return sbuHeadName;
	}

	public void setSbuHeadName(String sbuHeadName) {
		this.sbuHeadName = sbuHeadName;
	}

	public String getSbuHeadDisplayName() {
		return sbuHeadDisplayName;
	}

	public void setSbuHeadDisplayName(String sbuHeadDisplayName) {
		this.sbuHeadDisplayName = sbuHeadDisplayName;
	}

	public String getSbuName() {
		return sbuName;
	}

	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
	}

	public LocalDate getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(LocalDate activeFrom) {
		this.activeFrom = activeFrom;
	}

	public LocalDate getActiveUntil() {
		return activeUntil;
	}

	public void setActiveUntil(LocalDate activeUntil) {
		this.activeUntil = activeUntil;
	}

	@Override
	public String toString() {
		return "StrategicBusinessUnitHeadVO [sbuHeadId=" + sbuHeadId + ", sbuHeadName=" + sbuHeadName
				+ ", sbuHeadDisplayName=" + sbuHeadDisplayName + ", sbuName=" + sbuName + ", activeFrom=" + activeFrom
				+ ", activeUntil=" + activeUntil + "]";
	}

	
}
