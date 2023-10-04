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
@Table(name = "CurrencyPermission")
@EntityListeners(AuditingEntityListener.class)
public class CurrencyPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "currency_permission_id")
	private Long currencyPermissionId;

	@Column(name = "currency_permission_all")
	private Long isCurrencyPermissionAll;

	@OneToMany(mappedBy = "currencyPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public CurrencyPermission() {

	}

	public CurrencyPermission(Long currencyPermissionId, Long isCurrencyPermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.currencyPermissionId = currencyPermissionId;
		this.isCurrencyPermissionAll = isCurrencyPermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getCurrencyPermissionId() {
		return currencyPermissionId;
	}

	public void setCurrencyPermissionId(Long currencyPermissionId) {
		this.currencyPermissionId = currencyPermissionId;
	}

	public Long getIsCurrencyPermissionAll() {
		return isCurrencyPermissionAll;
	}

	public void setIsCurrencyPermissionAll(Long isCurrencyPermissionAll) {
		this.isCurrencyPermissionAll = isCurrencyPermissionAll;
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
