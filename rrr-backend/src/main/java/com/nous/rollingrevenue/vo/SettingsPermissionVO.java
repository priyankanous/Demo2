package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class SettingsPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long settingsPermissionId;

	private boolean settingsPermissionAll;

	private boolean viewAll;

	private RolesPermissionVO rolesPermissionVO;

	private RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO;

	private ExplicitPermissionVO explicitPermissionVO;

	private AnnualTargetEntryPermissionVO AnnualTargetEntryPermissionVO;

	public Long getSettingsPermissionId() {
		return settingsPermissionId;
	}

	public void setSettingsPermissionId(Long settingsPermissionId) {
		this.settingsPermissionId = settingsPermissionId;
	}

	public boolean isSettingsPermissionAll() {
		return settingsPermissionAll;
	}

	public void setSettingsPermissionAll(boolean settingsPermissionAll) {
		this.settingsPermissionAll = settingsPermissionAll;
	}

	public boolean isViewAll() {
		return viewAll;
	}

	public void setViewAll(boolean viewAll) {
		this.viewAll = viewAll;
	}

	public RolesPermissionVO getRolesPermissionVO() {
		return rolesPermissionVO;
	}

	public void setRolesPermissionVO(RolesPermissionVO rolesPermissionVO) {
		this.rolesPermissionVO = rolesPermissionVO;
	}

	public RoleUserAssignmentPermissionVO getRoleUserAssignmentPermissionVO() {
		return roleUserAssignmentPermissionVO;
	}

	public void setRoleUserAssignmentPermissionVO(RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO) {
		this.roleUserAssignmentPermissionVO = roleUserAssignmentPermissionVO;
	}

	public ExplicitPermissionVO getExplicitPermissionVO() {
		return explicitPermissionVO;
	}

	public void setExplicitPermissionVO(ExplicitPermissionVO explicitPermissionVO) {
		this.explicitPermissionVO = explicitPermissionVO;
	}

	public AnnualTargetEntryPermissionVO getAnnualTargetEntryPermissionVO() {
		return AnnualTargetEntryPermissionVO;
	}

	public void setAnnualTargetEntryPermissionVO(AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO) {
		AnnualTargetEntryPermissionVO = annualTargetEntryPermissionVO;
	}

}
