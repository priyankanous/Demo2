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
@Table(name = "sbu_permission")
@EntityListeners(AuditingEntityListener.class)
public class SBUPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sbu_permission_id")
	private Long sbuPermissionId;

	@Column(name = "sbu_permission_all")
	private boolean isSbuPermissionAll;

	@OneToMany(mappedBy = "sbuPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public SBUPermission() {

	}

	public SBUPermission(Long sbuPermissionId, boolean isSbuPermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.sbuPermissionId = sbuPermissionId;
		this.isSbuPermissionAll = isSbuPermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getSbuPermissionId() {
		return sbuPermissionId;
	}

	public void setSbuPermissionId(Long sbuPermissionId) {
		this.sbuPermissionId = sbuPermissionId;
	}

	public boolean isSbuPermissionAll() {
		return isSbuPermissionAll;
	}

	public void setSbuPermissionAll(boolean isSbuPermissionAll) {
		this.isSbuPermissionAll = isSbuPermissionAll;
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
