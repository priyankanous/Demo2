package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class RolesPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roles__permission_id")
	private Long rolesPermissionId;

	@Column(name = "rolesPermissionAll")
	private boolean isRolesPermissionAll;

	@Column(name = "delete")
	private boolean isDeleteRequired;

	@Column(name = "create_role")
	private boolean isCreateRoleRequired;

	@Column(name = "edit_role")
	private boolean isEditRoleRequired;

	@OneToMany(mappedBy = "rolesPermission")
	@JsonBackReference
	private List<SettingsPermission> settingsPermission = new ArrayList<>();

}
