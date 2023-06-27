package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bdm_meeting")
@EntityListeners(AuditingEntityListener.class)
public class BDMMeeting extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bdm_meeting_id")
	private Long bdmMeetingId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "bdm_id", referencedColumnName = "bdm_id")
	private BusinessDevelopmentManager businessDevelopmentManager;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region region;
	
	@Column(name = "meeting_name")
	private String meetingName;
	
	@Column(name = "meeting_date")
	private LocalDate meetingDate;
	
	@Column(name = "meeting_time")
	private LocalTime meetingTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;
	

	public BDMMeeting() {

	}

	public BDMMeeting(Long bdmMeetingId, BusinessDevelopmentManager businessDevelopmentManager, Region region, String meetingName, LocalDate meetingDate,
			LocalTime meetingTime, FinancialYear financialYear) {
		super();
		this.bdmMeetingId = bdmMeetingId;
		this.businessDevelopmentManager = businessDevelopmentManager;
		this.region = region;
		this.meetingName = meetingName;
		this.meetingDate = meetingDate;
		this.meetingTime = meetingTime;
		this.financialYear = financialYear;
	}


	public Long getBdmMeetingId() {
		return bdmMeetingId;
	}

	public void setBdmMeetingId(Long bdmMeetingId) {
		this.bdmMeetingId = bdmMeetingId;
	}

	public BusinessDevelopmentManager getBusinessDevelopmentManager() {
		return businessDevelopmentManager;
	}

	public void setBusinessDevelopmentManager(BusinessDevelopmentManager businessDevelopmentManager) {
		this.businessDevelopmentManager = businessDevelopmentManager;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
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


}
