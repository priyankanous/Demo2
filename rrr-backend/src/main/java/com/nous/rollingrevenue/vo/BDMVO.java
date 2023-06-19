package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BDMVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long bdmId;

	@NotBlank(message = "BDMName cannot be null or empty")
	private String bdmName;

	@NotBlank(message = "BDMDisplayName cannot be null or empty")
	private String bdmDisplayName;

	@NotNull(message = "activeFrom cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate activeFrom;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate activeUntil;

	private List<BusinessUnitVO> businessUnits = new ArrayList<>();

	private List<RegionVO> regions = new ArrayList<>();

	private boolean isActive;

	public BDMVO() {

	}

	public BDMVO(Long bdmId, String bdmName, String bdmDisplayName, LocalDate activeFrom, LocalDate activeUntil,
			List<BusinessUnitVO> businessUnits, List<RegionVO> regions, boolean isActive) {
		super();
		this.bdmId = bdmId;
		this.bdmName = bdmName;
		this.bdmDisplayName = bdmDisplayName;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
		this.businessUnits = businessUnits;
		this.regions = regions;
		this.isActive = isActive;
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

	public List<BusinessUnitVO> getBusinessUnits() {
		return businessUnits;
	}

	public void setBusinessUnits(List<BusinessUnitVO> businessUnits) {
		this.businessUnits = businessUnits;
	}

	public List<RegionVO> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionVO> regions) {
		this.regions = regions;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
