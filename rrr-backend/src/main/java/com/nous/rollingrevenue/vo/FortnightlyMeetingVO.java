package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public class FortnightlyMeetingVO implements Serializable{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long meetingId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate meetingDate;
	
	@NotBlank(message = "FinancialYear cannot be null or empty")
	private String financialYear;

	public FortnightlyMeetingVO() {

	}

	public FortnightlyMeetingVO(Long meetingId, LocalDate meetingDate, String financialYear) {
		super();
		this.meetingId = meetingId;
		this.meetingDate = meetingDate;
		this.financialYear = financialYear;
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

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	@Override
	public String toString() {
		return "FortnightlyMeetingVO [meetingId=" + meetingId + ", meetingDate=" + meetingDate + ", financialYear="
				+ financialYear + "]";
	}

	
}
