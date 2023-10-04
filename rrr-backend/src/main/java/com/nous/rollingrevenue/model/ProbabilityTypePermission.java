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
@Table(name = "probability_type_permission")
@EntityListeners(AuditingEntityListener.class)
public class ProbabilityTypePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "probability_type_permission_id")
	private Long probabilityTypePermissionId;

	@Column(name = "probability_type_permission_all")
	private Boolean isProbabilityTypePermissionAll;

	@OneToMany(mappedBy = "probabilityTypePermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public ProbabilityTypePermission() {

	}

	public ProbabilityTypePermission(Long probabilityTypePermissionId, Boolean isProbabilityTypePermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.probabilityTypePermissionId = probabilityTypePermissionId;
		this.isProbabilityTypePermissionAll = isProbabilityTypePermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getProbabilityTypePermissionId() {
		return probabilityTypePermissionId;
	}

	public void setProbabilityTypePermissionId(Long probabilityTypePermissionId) {
		this.probabilityTypePermissionId = probabilityTypePermissionId;
	}

	public Boolean getIsProbabilityTypePermissionAll() {
		return isProbabilityTypePermissionAll;
	}

	public void setIsProbabilityTypePermissionAll(Boolean isProbabilityTypePermissionAll) {
		this.isProbabilityTypePermissionAll = isProbabilityTypePermissionAll;
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
