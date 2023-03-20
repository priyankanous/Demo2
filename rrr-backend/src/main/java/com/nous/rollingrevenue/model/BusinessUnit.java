package com.nous.rollingrevenue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "business_unit")
public class BusinessUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bu_id")
	private Long businessUnitId;

	@Column(name = "bu_name")
	private String businessUnitName;

	@Column(name = "bu_display_name")
	private String businessUnitDisplayName;

	@Column(name = "organization_name")
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

	public BusinessUnit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessUnit(Long businessUnitId, String businessUnitName, String businessUnitDisplayName,
			String childOfOrg) {
		super();
		this.businessUnitId = businessUnitId;
		this.businessUnitName = businessUnitName;
		this.businessUnitDisplayName = businessUnitDisplayName;
		this.childOfOrg = childOfOrg;
	}

}
