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
	
	private Long bdmMeetingId;

	private BDMVO businessDevelopmentManager ;

	private RegionVO region;

	@NotBlank(message = "Meeting Name cannot be null or empty")
	private String meetingName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate meetingDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime meetingTime;
	
	private FinancialYearVO financialYear;

	private boolean isActive;

	public BDMMeetingVO() {

	}

	public BDMMeetingVO(Long bdmMeetingId, BDMVO businessDevelopmentManager, RegionVO region, String meetingName, LocalDate meetingDate,
			LocalTime meetingTime, FinancialYearVO financialYear,  boolean isActive) {
		super();
		this.bdmMeetingId = bdmMeetingId;
		this.businessDevelopmentManager = businessDevelopmentManager;
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

	public BDMVO getBusinessDevelopmentManager() {
		return businessDevelopmentManager;
	}

	public void setBusinessDevelopmentManager(BDMVO businessDevelopmentManager) {
		this.businessDevelopmentManager = businessDevelopmentManager;
	}

	public RegionVO getRegion() {
		return region;
	}

	public void setRegion(RegionVO region) {
		this.region = region;
	}

	public FinancialYearVO getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYearVO financialYear) {
		this.financialYear = financialYear;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
