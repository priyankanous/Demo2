package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class BusinessUnitVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "bui", access = JsonProperty.Access.READ_ONLY)
	private Long businessUnitId;

	@JsonProperty("bun")
	@NotEmpty(message = "business unit name must not be empty")
	private String businessUnitName;

	@JsonProperty("bdn")
	@NotEmpty(message = "business unit display name must not be empty")
	private String businessUnitDisplayName;

	@JsonProperty("coo")
	@NotEmpty(message = "child of organization must not be empty")
	private String childOfOrg;

	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getBusinessUnitDisplayName() {
		return businessUnitDisplayName;
	}

	public void setBusinessUnitDisplayName(String businessUnitDisplayName) {
		this.businessUnitDisplayName = businessUnitDisplayName;
	}

	public String getChildOfOrg() {
		return childOfOrg;
	}

	public void setChildOfOrg(String childOfOrg) {
		this.childOfOrg = childOfOrg;
	}

}
