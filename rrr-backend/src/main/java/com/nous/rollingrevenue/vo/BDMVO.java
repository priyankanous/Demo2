package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

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

	@NotEmpty(message = "Atleast one BusinessUnit is required")
	private Set<String> linkedToBusinessUnit = new HashSet<>();

	@NotEmpty(message = "Atleast one Region is required")
	private Set<String> linkedToRegion = new HashSet<>();

	public BDMVO() {

	}

	public BDMVO(Long bdmId, String bdmName, String bdmDisplayName, LocalDate activeFrom, LocalDate activeUntil,
			Set<String> linkedToBusinessUnit, Set<String> linkedToRegion) {
		this.bdmId = bdmId;
		this.bdmName = bdmName;
		this.bdmDisplayName = bdmDisplayName;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
		this.linkedToBusinessUnit = linkedToBusinessUnit;
		this.linkedToRegion = linkedToRegion;
	}

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

	public Set<String> getLinkedToBusinessUnit() {
		return linkedToBusinessUnit;
	}

	public void setLinkedToBusinessUnit(Set<String> linkedToBusinessUnit) {
		this.linkedToBusinessUnit = linkedToBusinessUnit;
	}

	public Set<String> getLinkedToRegion() {
		return linkedToRegion;
	}

	public void setLinkedToRegion(Set<String> linkedToRegion) {
		this.linkedToRegion = linkedToRegion;
	}

	@Override
	public String toString() {
		return "BDMVO [bdmId=" + bdmId + ", bdmName=" + bdmName + ", bdmDisplayName=" + bdmDisplayName + ", activeFrom="
				+ activeFrom + ", activeUntil=" + activeUntil + ", linkedToBusinessUnit=" + linkedToBusinessUnit
				+ ", linkedToRegion=" + linkedToRegion + "]";
	}

}
