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
public class COCPractisePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coc_practise_permission_id")
	private Long cocPractisePermissionId;

	@Column(name = "coc_practise_permission_all")
	private Boolean isCocPractisePermissionAll;

	@OneToMany(mappedBy = "cocPractisePermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public COCPractisePermission() {

	}

	public COCPractisePermission(Long cocPractisePermissionId, Boolean isCocPractisePermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.cocPractisePermissionId = cocPractisePermissionId;
		this.isCocPractisePermissionAll = isCocPractisePermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getCocPractisePermissionId() {
		return cocPractisePermissionId;
	}

	public void setCocPractisePermissionId(Long cocPractisePermissionId) {
		this.cocPractisePermissionId = cocPractisePermissionId;
	}

	public Boolean getIsCocPractisePermissionAll() {
		return isCocPractisePermissionAll;
	}

	public void setIsCocPractisePermissionAll(Boolean isCocPractisePermissionAll) {
		this.isCocPractisePermissionAll = isCocPractisePermissionAll;
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
