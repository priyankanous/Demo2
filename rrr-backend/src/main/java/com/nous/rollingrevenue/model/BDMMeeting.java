package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bdm_meeting")
@EntityListeners(AuditingEntityListener.class)
public class BDMMeeting extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bdm_meeting_id")
	private Long bdmMeetingId;
	
	@Column(name = "bdm_name")
	private String bdmName;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "meeting_name")
	private String meetingName;
	
	@Column(name = "meeting_date")
	private LocalDate meetingDate;
	
	@Column(name = "meeting_time")
	private LocalTime meetingTime;
	
	@Column(name = "financial_year")
	private String financialYear;
	
	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public BDMMeeting() {

	}

	public BDMMeeting(Long bdmMeetingId, String bdmName, String region, String meetingName, LocalDate meetingDate,
			LocalTime meetingTime, String financialYear, boolean isActive) {
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

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BDMMeeting [bdmMeetingId=" + bdmMeetingId + ", bdmName=" + bdmName + ", region=" + region
				+ ", meetingName=" + meetingName + ", meetingDate=" + meetingDate + ", meetingTime=" + meetingTime
				+ ", financialYear=" + financialYear + ", isActive=" + isActive + "]";
	}


}
