package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class StrategicBusinessUnitHeadVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long sbuHeadId;

	@NotBlank(message = "SBUHeadName cannot be null or empty")
	private String sbuHeadName;

	@NotBlank(message = "SBUHeadDisplayName cannot be null or empty")
	private String sbuHeadDisplayName;

	private StrategicBusinessUnitVO strategicBusinessUnitVO;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate activeFrom;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate activeUntil;

	private boolean isActive;

	public StrategicBusinessUnitHeadVO() {

	}

	public StrategicBusinessUnitHeadVO(Long sbuHeadId, String sbuHeadName, String sbuHeadDisplayName, StrategicBusinessUnitVO strategicBusinessUnitVO,
			LocalDate activeFrom, LocalDate activeUntil, boolean isActive) {
		super();
		this.sbuHeadId = sbuHeadId;
		this.sbuHeadName = sbuHeadName;
		this.sbuHeadDisplayName = sbuHeadDisplayName;
		this.strategicBusinessUnitVO = strategicBusinessUnitVO;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
		this.isActive = isActive;
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

	public StrategicBusinessUnitVO getStrategicBusinessUnitVO() {
		return strategicBusinessUnitVO;
	}

	public void setStrategicBusinessUnitVO(StrategicBusinessUnitVO strategicBusinessUnitVO) {
		this.strategicBusinessUnitVO = strategicBusinessUnitVO;
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

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "StrategicBusinessUnitHeadVO [sbuHeadId=" + sbuHeadId + ", sbuHeadName=" + sbuHeadName
				+ ", sbuHeadDisplayName=" + sbuHeadDisplayName + ", strategicBusinessUnitVO=" + strategicBusinessUnitVO
				+ ", activeFrom=" + activeFrom + ", activeUntil=" + activeUntil + ", isActive=" + isActive + "]";
	}


}
