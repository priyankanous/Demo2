package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

public class FortnightlyMeetingVO implements Serializable{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long meetingId;
	
	private LocalDate meetingDate;
	
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
