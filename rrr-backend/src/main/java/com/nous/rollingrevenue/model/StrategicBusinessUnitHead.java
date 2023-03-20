package com.nous.rollingrevenue.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "strategic_business_unit_head")
public class StrategicBusinessUnitHead {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sbu_head_id")
	private Long sbuHeadId;
	
	@Column(name = "sbu_head_name")
	private String sbuHeadName;

	@Column(name = "sbu_head_display_name")
	private String sbuHeadDisplayName;

	@Column(name = "sbu_name")
	private String sbuName;

	@Column(name = "active_from")
	private LocalDate activeFrom;

	@Column(name = "active_until")
	private LocalDate activeUntil;

	public StrategicBusinessUnitHead() {

	}

	public StrategicBusinessUnitHead(Long sbuHeadId, String sbuHeadName, String sbuHeadDisplayName, String sbuName,
			LocalDate activeFrom, LocalDate activeUntil) {
		super();
		this.sbuHeadId = sbuHeadId;
		this.sbuHeadName = sbuHeadName;
		this.sbuHeadDisplayName = sbuHeadDisplayName;
		this.sbuName = sbuName;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
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

	public String getSbuName() {
		return sbuName;
	}

	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
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

	@Override
	public String toString() {
		return "StrategicBusinessUnitHead [sbuHeadId=" + sbuHeadId + ", sbuHeadName=" + sbuHeadName
				+ ", sbuHeadDisplayName=" + sbuHeadDisplayName + ", sbuName=" + sbuName + ", activeFrom=" + activeFrom
				+ ", activeUntil=" + activeUntil + "]";
	}


}
