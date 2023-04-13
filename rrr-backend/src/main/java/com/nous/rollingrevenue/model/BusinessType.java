package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	

	public BusinessType() {

	}

	public BusinessType(Long businessTypeId, String businessTypeName, String businessTypeDisplayName) {
		super();
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
		return "BusinessType [businessTypeId=" + businessTypeId + ", businessTypeName=" + businessTypeName
				+ ", businessTypeDisplayName=" + businessTypeDisplayName + "]";
	}

}
