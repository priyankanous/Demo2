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
public class BillingType  extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bt_id")
	private Long billingTypeId;

	@Column(name = "bt_name")
	private String billingTypeName;

	@Column(name = "bt_display_name")
	private String billingTypeDisplayName;
}
