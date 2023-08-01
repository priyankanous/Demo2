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
@Table(name = "billing_type")
@EntityListeners(AuditingEntityListener.class)
public class BillingType extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bt_id")
	private Long billingTypeId;

	@Column(name = "bt_name")
	private String billingTypeName;

	@Column(name = "bt_display_name")
	private String billingTypeDisplayName;

	public BillingType() {
		
	}

	public BillingType(Long billingTypeId, String billingTypeName, String billingTypeDisplayName) {
		super();
		this.billingTypeId = billingTypeId;
		this.billingTypeName = billingTypeName;
		this.billingTypeDisplayName = billingTypeDisplayName;
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

}
