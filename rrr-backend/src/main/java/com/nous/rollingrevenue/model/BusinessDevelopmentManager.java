package com.nous.rollingrevenue.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buiness_development_manager")
public class BusinessDevelopmentManager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bdm_id")
	private Long bdmId;

	@Column(name = "bdm_name")
	private String bdmName;

	@Column(name = "bdm_display_name")
	private String bdmDisplayName;

	@Column(name = "active_from")
	private LocalDate activeFrom;

	@Column(name = "active_until")
	private LocalDate activeUntil;

	@Column(name = "linked_to_bu")
	private String linkedToBusinessUnit;

	@Column(name = "linked_to_region")
	private String linkedToRegion;

	public Long getBdmId() {
		return bdmId;
	}

	public void setBdmId(Long bdmId) {
		this.bdmId = bdmId;
	}

	public String getBdmName() {
		return bdmName;
	}

	public void setBdmName(String bdmName) {
		this.bdmName = bdmName;
	}

	public String getBdmDisplayName() {
		return bdmDisplayName;
	}

	public void setBdmDisplayName(String bdmDisplayName) {
		this.bdmDisplayName = bdmDisplayName;
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

	public String getLinkedToBusinessUnit() {
		return linkedToBusinessUnit;
	}

	public void setLinkedToBusinessUnit(String linkedToBusinessUnit) {
		this.linkedToBusinessUnit = linkedToBusinessUnit;
	}

	public String getLinkedToRegion() {
		return linkedToRegion;
	}

	public void setLinkedToRegion(String linkedToRegion) {
		this.linkedToRegion = linkedToRegion;
	}

	public BusinessDevelopmentManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessDevelopmentManager(Long bdmId, String bdmName, String bdmDisplayName, LocalDate activeFrom,
			LocalDate activeUntil, String linkedToBusinessUnit, String linkedToRegion) {
		super();
		this.bdmId = bdmId;
		this.bdmName = bdmName;
		this.bdmDisplayName = bdmDisplayName;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
		this.linkedToBusinessUnit = linkedToBusinessUnit;
		this.linkedToRegion = linkedToRegion;
	}

}
