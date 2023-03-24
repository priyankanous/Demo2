package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class PricingTypeVO implements Serializable {
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long pricingTypeId;
	
	@NotBlank(message = "PricingTypeName cannot be null or empty")
	private String pricingTypeName;
	
	@NotBlank(message = "PricingTypeDisplayName cannot be null or empty")
	private String pricingTypeDisplayName;

	public PricingTypeVO() {

	}

	public PricingTypeVO(Long pricingTypeId, String pricingTypeName, String pricingTypeDisplayName) {
		this.pricingTypeId = pricingTypeId;
		this.pricingTypeName = pricingTypeName;
		this.pricingTypeDisplayName = pricingTypeDisplayName;
	}

	public Long getPricingTypeId() {
		return pricingTypeId;
	}

	public void setPricingTypeId(Long pricingTypeId) {
		this.pricingTypeId = pricingTypeId;
	}

	public String getPricingTypeName() {
		return pricingTypeName;
	}

	public void setPricingTypeName(String pricingTypeName) {
		this.pricingTypeName = pricingTypeName;
	}

	public String getPricingTypeDisplayName() {
		return pricingTypeDisplayName;
	}

	public void setPricingTypeDisplayName(String pricingTypeDisplayName) {
		this.pricingTypeDisplayName = pricingTypeDisplayName;
	}

	@Override
	public String toString() {
		return "PricingTypeVO [pricingTypeId=" + pricingTypeId + ", pricingTypeName=" + pricingTypeName
				+ ", pricingTypeDisplayName=" + pricingTypeDisplayName + "]";
	}
	

}
