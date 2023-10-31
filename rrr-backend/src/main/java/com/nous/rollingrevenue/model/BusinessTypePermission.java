package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "business_type_permission")
@EntityListeners(AuditingEntityListener.class)
public class BusinessTypePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_type_permission_id")
	private Long businessTypePermissionId;

	@Column(name = "business_type_permission_all")
	private boolean isBusinessTypePermissionAll;

	@OneToMany(mappedBy = "businessTypePermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public BusinessTypePermission() {

	}

	public BusinessTypePermission(Long businessTypePermissionId, boolean isBusinessTypePermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.businessTypePermissionId = businessTypePermissionId;
		this.isBusinessTypePermissionAll = isBusinessTypePermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getBusinessTypePermissionId() {
		return businessTypePermissionId;
	}

	public void setBusinessTypePermissionId(Long businessTypePermissionId) {
		this.businessTypePermissionId = businessTypePermissionId;
	}

	public boolean isBusinessTypePermissionAll() {
		return isBusinessTypePermissionAll;
	}

	public void setBusinessTypePermissionAll(boolean isBusinessTypePermissionAll) {
		this.isBusinessTypePermissionAll = isBusinessTypePermissionAll;
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
