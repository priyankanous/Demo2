package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class BillingTypeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long billingTypeId;

	@NotEmpty(message = "billing type name must not be empty")
	private String billingTypeName;

	@NotEmpty(message = "billing type display name must not be empty")
	private String billingTypeDisplayName;

	private boolean isActive;

	public BillingTypeVO() {
		super();
	}

	public BillingTypeVO(Long billingTypeId, String billingTypeName, String billingTypeDisplayName, boolean isActive) {
		super();
		this.billingTypeId = billingTypeId;
		this.billingTypeName = billingTypeName;
		this.billingTypeDisplayName = billingTypeDisplayName;
		this.isActive = isActive;
	}

	public Long getBillingTypeId() {
		return billingTypeId;
	}

	public void setBillingTypeId(Long billingTypeId) {
		this.billingTypeId = billingTypeId;
	}

	public String getBillingTypeName() {
		return billingTypeName;
	}

	public void setBillingTypeName(String billingTypeName) {
		this.billingTypeName = billingTypeName;
	}

	public String getBillingTypeDisplayName() {
		return billingTypeDisplayName;
	}

	public void setBillingTypeDisplayName(String billingTypeDisplayName) {
		this.billingTypeDisplayName = billingTypeDisplayName;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
