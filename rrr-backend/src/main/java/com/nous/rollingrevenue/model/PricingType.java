package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pricing_type")
@EntityListeners(AuditingEntityListener.class)
@Data
public class PricingType extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pricing_type_id")
	private Long pricingTypeId;

	@Column(name = "pricing_type_name")
	private String pricingTypeName;

	@Column(name = "pricing_type_display_name")
	private String pricingTypeDisplayName;

}
