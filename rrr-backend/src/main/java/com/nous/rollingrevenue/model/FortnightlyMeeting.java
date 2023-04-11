package com.nous.rollingrevenue.model;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fortnightly_meeting")
@EntityListeners(AuditingEntityListener.class)
public class FortnightlyMeeting extends Auditable<String> {

	@Id
	@Column(name = "meeting_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingId;

	@Column(name = "meeting_date")
	private LocalDate meetingDate;
	
	@Column(name = "meeting_day")
	private String meetingDay;

	@Column(name = "financial_year")
	private String financialYear;
	
	@Column(name = "meeting_name1")
	private String meetingName1;

	@Column(name = "meeting_name2")
	private String meetingName2;

	@Column(name = "meeting_name3")
	private String meetingName3;

	@Column(name = "meeting_name4")
	private String meetingName4;

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public FortnightlyMeeting() {

	}

	public FortnightlyMeeting(Long meetingId, LocalDate meetingDate, String meetingDay, String financialYear,
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

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "FortnightlyMeeting [meetingId=" + meetingId + ", meetingDate=" + meetingDate + ", meetingDay="
				+ meetingDay + ", financialYear=" + financialYear + ", meetingName1=" + meetingName1 + ", meetingName2="
				+ meetingName2 + ", meetingName3=" + meetingName3 + ", meetingName4=" + meetingName4 + ", isActive="
				+ isActive + "]";
	}

}
