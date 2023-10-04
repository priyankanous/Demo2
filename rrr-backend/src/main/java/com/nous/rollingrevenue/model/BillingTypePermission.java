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
@Table(name = "billing_type_permission")
@EntityListeners(AuditingEntityListener.class)
public class BillingTypePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billing_type_permission_id")
	private Long billingTypePermissionId;

	@Column(name = "billing_type_permission_all")
	private Boolean isBillingTypePermissionAll;

	@OneToMany(mappedBy = "billingTypePermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public BillingTypePermission() {

	}

	public BillingTypePermission(Long billingTypePermissionId, Boolean isBillingTypePermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.billingTypePermissionId = billingTypePermissionId;
		this.isBillingTypePermissionAll = isBillingTypePermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getBillingTypePermissionId() {
		return billingTypePermissionId;
	}

	public void setBillingTypePermissionId(Long billingTypePermissionId) {
		this.billingTypePermissionId = billingTypePermissionId;
	}

	public Boolean getIsBillingTypePermissionAll() {
		return isBillingTypePermissionAll;
	}

	public void setIsBillingTypePermissionAll(Boolean isBillingTypePermissionAll) {
		this.isBillingTypePermissionAll = isBillingTypePermissionAll;
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
