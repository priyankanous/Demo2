package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "business_type")
@EntityListeners(AuditingEntityListener.class)
public class BusinessType extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_type_id")
	private Long businessTypeId;

	@Column(name = "business_type_name")
	private String businessTypeName;

	@Column(name = "business_type_display_name")
	private String businessTypeDisplayName;

	@OneToMany(mappedBy = "businessType")
	private List<RollingRevenueCommonEntry> rollingRevenueCommonEntry = new ArrayList<>();

	@OneToMany(mappedBy = "businessType")
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	public BusinessType() {

	}

	public BusinessType(Long businessTypeId, String businessTypeName, String businessTypeDisplayName,
			List<AnnualTargetEntry> annualTargetEntries, List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		super();
		this.businessTypeId = businessTypeId;
		this.businessTypeName = businessTypeName;
		this.businessTypeDisplayName = businessTypeDisplayName;
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
		this.annualTargetEntries = annualTargetEntries;
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

	public List<RollingRevenueCommonEntry> getRollingRevenueCommonEntry() {
		return rollingRevenueCommonEntry;
	}

	public void setRollingRevenueCommonEntry(List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
	}

	public List<AnnualTargetEntry> getAnnualTargetEntries() {
		return annualTargetEntries;
	}

	public void setAnnualTargetEntries(List<AnnualTargetEntry> annualTargetEntries) {
		this.annualTargetEntries = annualTargetEntries;
	}

}
