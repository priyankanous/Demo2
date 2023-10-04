package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pricing_type_permission")
@EntityListeners(AuditingEntityListener.class)
public class PricingTypePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pricing_type_permission_id")
	private Long pricingTypePermissionId;

	@Column(name = "pricing_type_permission_all")
	private Boolean isPricingTypePermissionAll;

	@OneToMany(mappedBy = "pricingTypePermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public PricingTypePermission() {

	}

	public PricingTypePermission(Long pricingTypePermissionId, Boolean isPricingTypePermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.pricingTypePermissionId = pricingTypePermissionId;
		this.isPricingTypePermissionAll = isPricingTypePermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getPricingTypePermissionId() {
		return pricingTypePermissionId;
	}

	public void setPricingTypePermissionId(Long pricingTypePermissionId) {
		this.pricingTypePermissionId = pricingTypePermissionId;
	}

	public Boolean getIsPricingTypePermissionAll() {
		return isPricingTypePermissionAll;
	}

	public void setIsPricingTypePermissionAll(Boolean isPricingTypePermissionAll) {
		this.isPricingTypePermissionAll = isPricingTypePermissionAll;
	}

	public List<AdministrationPermission> getAdministrationPermission() {
		return administrationPermission;
	}

	public void setAdministrationPermission(List<AdministrationPermission> administrationPermission) {
		this.administrationPermission = administrationPermission;
	}

	public AdministrationCommonPermission getAdministrationCommonPermission() {
		return administrationCommonPermission;
	}

	public void setAdministrationCommonPermission(AdministrationCommonPermission administrationCommonPermission) {
		this.administrationCommonPermission = administrationCommonPermission;
	}

}
