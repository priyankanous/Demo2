package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class BusinessTypeVO implements Serializable {
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long businessTypeId;
	
	@NotBlank(message = "BusinessTypeName cannot be null or empty")
	private String businessTypeName;
	
	@NotBlank(message = "BusinessTypeDisplayName cannot be null or empty")
	private String businessTypeDisplayName;

	public BusinessTypeVO() {

	}

	public BusinessTypeVO(Long businessTypeId, String businessTypeName, String businessTypeDisplayName) {
		this.businessTypeId = businessTypeId;
		this.businessTypeName = businessTypeName;
		this.businessTypeDisplayName = businessTypeDisplayName;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public String getBusinessTypeDisplayName() {
		return businessTypeDisplayName;
	}

	public void setBusinessTypeDisplayName(String businessTypeDisplayName) {
		this.businessTypeDisplayName = businessTypeDisplayName;
	}

	@Override
	public String toString() {
		return "BusinessTypeVO [businessTypeId=" + businessTypeId + ", businessTypeName=" + businessTypeName
				+ ", businessTypeDisplayName=" + businessTypeDisplayName + "]";
	}
	

}
