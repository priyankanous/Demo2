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
@Table(name = "business_unit_permission")
@EntityListeners(AuditingEntityListener.class)
public class BusinessUnitPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_unit_permission_id")
	private Long businessUnitPermissionId;

	@Column(name = "business_unit_permission_all")
	private Boolean isBusinessUnitPermissionAll;

	@OneToMany(mappedBy = "businessUnitPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public BusinessUnitPermission() {

	}

	public BusinessUnitPermission(Long businessUnitPermissionId, Boolean isBusinessUnitPermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.businessUnitPermissionId = businessUnitPermissionId;
		this.isBusinessUnitPermissionAll = isBusinessUnitPermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getBusinessUnitPermissionId() {
		return businessUnitPermissionId;
	}

	public void setBusinessUnitPermissionId(Long businessUnitPermissionId) {
		this.businessUnitPermissionId = businessUnitPermissionId;
	}

	public Boolean getIsBusinessUnitPermissionAll() {
		return isBusinessUnitPermissionAll;
	}

	public void setIsBusinessUnitPermissionAll(Boolean isBusinessUnitPermissionAll) {
		this.isBusinessUnitPermissionAll = isBusinessUnitPermissionAll;
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
