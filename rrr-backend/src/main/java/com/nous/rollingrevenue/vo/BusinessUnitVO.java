package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class BusinessUnitVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long businessUnitId;

	@NotEmpty(message = "business unit name must not be empty")
	private String businessUnitName;

	@NotEmpty(message = "business unit display name must not be empty")
	private String businessUnitDisplayName;

	private OrganizationVO organizationVO;

	private boolean isActive;

	public BusinessUnitVO() {
		super();
	}

	public BusinessUnitVO(Long businessUnitId, String businessUnitName, String businessUnitDisplayName,
			OrganizationVO organizationVO, boolean isActive) {
		super();
		this.businessUnitId = businessUnitId;
		this.businessUnitName = businessUnitName;
		this.businessUnitDisplayName = businessUnitDisplayName;
		this.organizationVO = organizationVO;
		this.isActive = isActive;
	}

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

	public OrganizationVO getOrganizationVO() {
		return organizationVO;
	}

	public void setOrganizationVO(OrganizationVO organizationVO) {
		this.organizationVO = organizationVO;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BusinessUnitVO [businessUnitId=" + businessUnitId + ", businessUnitName=" + businessUnitName
				+ ", businessUnitDisplayName=" + businessUnitDisplayName + ", organizationVO=" + organizationVO
				+ ", isActive=" + isActive + "]";
	}


}
