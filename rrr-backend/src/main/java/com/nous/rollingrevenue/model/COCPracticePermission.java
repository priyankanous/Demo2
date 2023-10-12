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
@Table(name = "coc_practise_permission")
@EntityListeners(AuditingEntityListener.class)
public class COCPracticePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coc_practice_permission_id")
	private Long cocPracticePermissionId;

	@Column(name = "coc_practice_permission_all")
	private boolean isCocPracticePermissionAll;

	@OneToMany(mappedBy = "cocPracticePermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public COCPracticePermission() {

	}

	public COCPracticePermission(Long cocPracticePermissionId, boolean isCocPracticePermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.cocPracticePermissionId = cocPracticePermissionId;
		this.isCocPracticePermissionAll = isCocPracticePermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getCocPracticePermissionId() {
		return cocPracticePermissionId;
	}

	public void setCocPracticePermissionId(Long cocPracticePermissionId) {
		this.cocPracticePermissionId = cocPracticePermissionId;
	}

	public boolean isCocPracticePermissionAll() {
		return isCocPracticePermissionAll;
	}

	public void setCocPracticePermissionAll(boolean isCocPracticePermissionAll) {
		this.isCocPracticePermissionAll = isCocPracticePermissionAll;
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
