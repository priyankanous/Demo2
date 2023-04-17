package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class FortnightlyMeetingVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long meetingId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate meetingDate;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String meetingDay;

	private FinancialYearVO financialYear;

	@NotBlank(message = "MeetingName1 cannot be null or empty")
	private String meetingName1;

	@NotBlank(message = "MeetingName2 cannot be null or empty")
	private String meetingName2;

	@NotBlank(message = "MeetingName3 cannot be null or empty")
	private String meetingName3;

	@NotBlank(message = "MeetingName4 cannot be null or empty")
	private String meetingName4;

	private boolean isActive;

	public FortnightlyMeetingVO() {

	}

	public FortnightlyMeetingVO(Long meetingId, LocalDate meetingDate, String meetingDay, FinancialYearVO financialYear,
			String meetingName1, String meetingName2, String meetingName3, String meetingName4, boolean isActive) {
		super();
		this.meetingId = meetingId;
		this.meetingDate = meetingDate;
		this.meetingDay = meetingDay;
		this.financialYear = financialYear;
		this.meetingName1 = meetingName1;
		this.meetingName2 = meetingName2;
		this.meetingName3 = meetingName3;
		this.meetingName4 = meetingName4;
		this.isActive = isActive;
	}

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

	public LocalDate getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingDay() {
		return meetingDay;
	}

	public void setMeetingDay(String meetingDay) {
		this.meetingDay = meetingDay;
	}

	public FinancialYearVO getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYearVO financialYear) {
		this.financialYear = financialYear;
	}

	public String getMeetingName1() {
		return meetingName1;
	}

	public void setMeetingName1(String meetingName1) {
		this.meetingName1 = meetingName1;
	}

	public String getMeetingName2() {
		return meetingName2;
	}

	public void setMeetingName2(String meetingName2) {
		this.meetingName2 = meetingName2;
	}

	public String getMeetingName3() {
		return meetingName3;
	}

	public void setMeetingName3(String meetingName3) {
		this.meetingName3 = meetingName3;
	}

	public String getMeetingName4() {
		return meetingName4;
	}

	public void setMeetingName4(String meetingName4) {
		this.meetingName4 = meetingName4;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
