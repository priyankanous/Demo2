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
@Table(name = "financial_year_permission")
@EntityListeners(AuditingEntityListener.class)
public class FinancialYearPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "financial_year_permission_id")
	private Long financialYearPermissionId;

	@Column(name = "financial_year_permission_all")
	private boolean isFinancialYearPermissionAll;

	@OneToMany(mappedBy = "financialYearPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public FinancialYearPermission() {

	}

	public FinancialYearPermission(Long financialYearPermissionId, boolean isFinancialYearPermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.financialYearPermissionId = financialYearPermissionId;
		this.isFinancialYearPermissionAll = isFinancialYearPermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getFinancialYearPermissionId() {
		return financialYearPermissionId;
	}

	public void setFinancialYearPermissionId(Long financialYearPermissionId) {
		this.financialYearPermissionId = financialYearPermissionId;
	}

	public boolean isFinancialYearPermissionAll() {
		return isFinancialYearPermissionAll;
	}

	public void setFinancialYearPermissionAll(boolean isFinancialYearPermissionAll) {
		this.isFinancialYearPermissionAll = isFinancialYearPermissionAll;
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
