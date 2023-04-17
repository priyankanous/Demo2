package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class RegionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long regionId;

	@NotBlank(message = "RegionName cannot be null or empty")
	private String regionName;

	@NotBlank(message = "RegionDisplayName cannot be null or empty")
	private String regionDisplayName;

	private boolean isActive;

	public RegionVO() {

	}

	public RegionVO(Long regionId, String regionName, String regionDisplayName, boolean isActive) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.regionDisplayName = regionDisplayName;
		this.isActive = isActive;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionDisplayName() {
		return regionDisplayName;
	}

	public void setRegionDisplayName(String regionDisplayName) {
		this.regionDisplayName = regionDisplayName;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
