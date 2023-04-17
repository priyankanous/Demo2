package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class LocationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long locationId;

	@NotEmpty(message = "LocationName must not be empty")
	private String locationName;

	@NotEmpty(message = "LocationDisplayName must not be empty")
	private String locationDisplayName;
	
	private boolean isActive;

	public LocationVO() {

	}

	public LocationVO(Long locationId, String locationName, String locationDisplayName, boolean isActive) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationDisplayName = locationDisplayName;
		this.isActive = isActive;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationDisplayName() {
		return locationDisplayName;
	}

	public void setLocationDisplayName(String locationDisplayName) {
		this.locationDisplayName = locationDisplayName;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
