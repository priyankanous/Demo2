package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BDMVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long bdmId;

	private String bdmName;

	private String bdmDisplayName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate activeFrom;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate activeUntil;

	private String linkedToBusinessUnit;

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

}
