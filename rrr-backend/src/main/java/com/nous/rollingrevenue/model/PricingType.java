package com.nous.rollingrevenue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pricing_type")
public class PricingType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pricing_type_id")
	private Long pricingTypeId;
	
	@Column(name = "pricing_type_name")
	private String pricingTypeName;
	
	@Column(name = "pricing_type_display_name")
	private String pricingTypeDisplayName;

	public PricingType() {

	}

	public PricingType(Long pricingTypeId, String pricingTypeName, String pricingTypeDisplayName) {
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
		return "PricingType [pricingTypeId=" + pricingTypeId + ", pricingTypeName=" + pricingTypeName
				+ ", pricingTypeDisplayName=" + pricingTypeDisplayName + "]";
	}
	

}
