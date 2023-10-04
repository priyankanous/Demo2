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

}
