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
@Table(name = "opportunity_permission")
@EntityListeners(AuditingEntityListener.class)
public class OpportunityPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "opportunity_permission_id")
	private Long opportunityPermissionId;

	@Column(name = "opportunity_permission_all")
	private Boolean isOpportunityPermissionAll;

	@OneToMany(mappedBy = "opportunityPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public OpportunityPermission() {

	}

	public OpportunityPermission(Long opportunityPermissionId, Boolean isOpportunityPermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.opportunityPermissionId = opportunityPermissionId;
		this.isOpportunityPermissionAll = isOpportunityPermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getOpportunityPermissionId() {
		return opportunityPermissionId;
	}

	public void setOpportunityPermissionId(Long opportunityPermissionId) {
		this.opportunityPermissionId = opportunityPermissionId;
	}

	public Boolean getIsOpportunityPermissionAll() {
		return isOpportunityPermissionAll;
	}

	public void setIsOpportunityPermissionAll(Boolean isOpportunityPermissionAll) {
		this.isOpportunityPermissionAll = isOpportunityPermissionAll;
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
