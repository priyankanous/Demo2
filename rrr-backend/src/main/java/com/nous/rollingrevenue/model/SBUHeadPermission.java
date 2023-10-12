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
@Table(name = "sbu_head_permission")
@EntityListeners(AuditingEntityListener.class)
public class SBUHeadPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sbu_head_permission_id")
	private Long sbuHeadPermissionId;

	@Column(name = "sbu_head_permission_all")
	private boolean isSbuHeadPermissionAll;

	@OneToMany(mappedBy = "sbuHeadPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public SBUHeadPermission() {

	}

	public SBUHeadPermission(Long sbuHeadPermissionId, boolean isSbuHeadPermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.sbuHeadPermissionId = sbuHeadPermissionId;
		this.isSbuHeadPermissionAll = isSbuHeadPermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getSbuHeadPermissionId() {
		return sbuHeadPermissionId;
	}

	public void setSbuHeadPermissionId(Long sbuHeadPermissionId) {
		this.sbuHeadPermissionId = sbuHeadPermissionId;
	}

	public boolean isSbuHeadPermissionAll() {
		return isSbuHeadPermissionAll;
	}

	public void setSbuHeadPermissionAll(boolean isSbuHeadPermissionAll) {
		this.isSbuHeadPermissionAll = isSbuHeadPermissionAll;
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
