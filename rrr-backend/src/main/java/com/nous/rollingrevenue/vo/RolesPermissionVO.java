package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RolesPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long rolesPermissionId;

	private boolean rolesPermissionAll;

	private boolean createRoleRequired;

	private boolean editRoleRequired;

	private boolean deleteRequired;

	private boolean isActive;

	public RolesPermissionVO() {

	}

	public RolesPermissionVO(Long rolesPermissionId, boolean rolesPermissionAll, boolean createRoleRequired,
			boolean editRoleRequired, boolean deleteRequired, boolean isActive) {
		super();
		this.rolesPermissionId = rolesPermissionId;
		this.rolesPermissionAll = rolesPermissionAll;
		this.createRoleRequired = createRoleRequired;
		this.editRoleRequired = editRoleRequired;
		this.deleteRequired = deleteRequired;
		this.isActive = isActive;
	}

	public Long getRolesPermissionId() {
		return rolesPermissionId;
	}

	public void setRolesPermissionId(Long rolesPermissionId) {
		this.rolesPermissionId = rolesPermissionId;
	}

	public boolean isRolesPermissionAll() {
		return rolesPermissionAll;
	}

	public void setRolesPermissionAll(boolean rolesPermissionAll) {
		this.rolesPermissionAll = rolesPermissionAll;
	}

	public boolean isCreateRoleRequired() {
		return createRoleRequired;
	}

	public void setCreateRoleRequired(boolean createRoleRequired) {
		this.createRoleRequired = createRoleRequired;
	}

	public boolean isEditRoleRequired() {
		return editRoleRequired;
	}

	public void setEditRoleRequired(boolean editRoleRequired) {
		this.editRoleRequired = editRoleRequired;
	}

	public boolean isDeleteRequired() {
		return deleteRequired;
	}

	public void setDeleteRequired(boolean deleteRequired) {
		this.deleteRequired = deleteRequired;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}