package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class BDMMeetingVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long bdmMeetingId;

	@NotBlank(message = "BDM Name cannot be null or empty")
	private String bdmName;

	@NotBlank(message = "Region cannot be null or empty")
	private String region;

	@NotBlank(message = "Meeting Name cannot be null or empty")
	private String meetingName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate meetingDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime meetingTime;
	
	@NotBlank(message = "FinancialYear Name cannot be null or empty")
	private String financialYear;

	private boolean isActive;

	public BDMMeetingVO() {

	}

	public BDMMeetingVO(Long bdmMeetingId, String bdmName, String region, String meetingName, LocalDate meetingDate,
			LocalTime meetingTime, String financialYear,  boolean isActive) {
		super();
		this.bdmMeetingId = bdmMeetingId;
		this.bdmName = bdmName;
		this.region = region;
		this.meetingName = meetingName;
		this.meetingDate = meetingDate;
		this.meetingTime = meetingTime;
		this.financialYear = financialYear;
		this.isActive = isActive;
	}

	public Long getBdmMeetingId() {
		return bdmMeetingId;
	}

	public void setBdmMeetingId(Long bdmMeetingId) {
		this.bdmMeetingId = bdmMeetingId;
	}

	public String getBdmName() {
		return bdmName;
	}

	public void setBdmName(String bdmName) {
		this.bdmName = bdmName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public LocalDate getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}

	public LocalTime getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(LocalTime meetingTime) {
		this.meetingTime = meetingTime;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	@Override
	public String toString() {
		return "BDMMeetingVO [bdmMeetingId=" + bdmMeetingId + ", bdmName=" + bdmName + ", region=" + region
				+ ", meetingName=" + meetingName + ", meetingDate=" + meetingDate + ", meetingTime=" + meetingTime
				+ ", financialYear=" + financialYear + ", isActive=" + isActive + "]";
	}
	

}
