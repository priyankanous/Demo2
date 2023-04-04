package com.nous.rollingrevenue.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fortnightly_meeting")
public class FortnightlyMeeting {
	
	@Id
	@Column(name = "meeting_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingId;
	
	@Column(name = "meeting_date")
	private LocalDate meetingDate;
	
	@Column(name = "financial_year")
	private String financialYear;
	

	public FortnightlyMeeting() {

	}

	public FortnightlyMeeting(Long meetingId, LocalDate meetingDate, String financialYear) {
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
		return "FortnightlyMeeting [meetingId=" + meetingId + ", meetingDate=" + meetingDate + ", financialYear="
				+ financialYear + "]";
	}

	
}
