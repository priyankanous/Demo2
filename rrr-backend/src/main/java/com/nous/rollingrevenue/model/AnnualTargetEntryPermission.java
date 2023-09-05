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
@Table(name = "annual_target_entry_permission")
@EntityListeners(AuditingEntityListener.class)
public class AnnualTargetEntryPermission extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "annual_target_entry__permission_id")
	private Long annualTargetEntryPermissionId;
	 
	@Column(name = "is_add_annual_target_entry_required")
	private Boolean isAddAnnualTargetEntryRequired;

	public AnnualTargetEntryPermission() {

	}

	public AnnualTargetEntryPermission(Long annualTargetEntryPermissionId, Boolean isAddAnnualTargetEntryRequired) {
		super();
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
		this.isAddAnnualTargetEntryRequired = isAddAnnualTargetEntryRequired;
	}

	public Long getAnnualTargetEntryPermissionId() {
		return annualTargetEntryPermissionId;
	}

	public void setAnnualTargetEntryPermissionId(Long annualTargetEntryPermissionId) {
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
	}

	public Boolean getIsAddAnnualTargetEntryRequired() {
		return isAddAnnualTargetEntryRequired;
	}

	public void setIsAddAnnualTargetEntryRequired(Boolean isAddAnnualTargetEntryRequired) {
		this.isAddAnnualTargetEntryRequired = isAddAnnualTargetEntryRequired;
	}
	
	
	
	
}
