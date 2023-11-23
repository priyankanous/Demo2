package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Roles;
import com.nous.rollingrevenue.vo.RolesVO;

@Component
public class RolesConverter {

	private RolesConverter() {
		super();
	}

	/**
	 * Convert RolesVO to Roles
	 * 
	 * @param RolesVO
	 * @return Roles
	 */
	public static Roles convertRolesVOToRoles(RolesVO rolesVO) {
		Roles roles = new Roles();
		if (rolesVO != null) {
			roles.setRoleId(rolesVO.getRoleId());
			roles.setRoleName(rolesVO.getRoleName());
			roles.setRoleDisplayName(rolesVO.getRoleDisplayName());
			roles.setSelectAll(rolesVO.isSelectAllPermissions());
		}
		return roles;
	}

	/**
	 * Convert Roles to RolesVO
	 * 
	 * @param Roles
	 * @return RolesVO
	 */
	public static RolesVO convertRolesToRolesVO(Roles roles) {
		RolesVO rolesVO = new RolesVO();
		if (roles != null) {
			rolesVO.setRoleId(roles.getRoleId());
			rolesVO.setRoleName(roles.getRoleName());
			rolesVO.setRoleDisplayName(roles.getRoleDisplayName());
			rolesVO.setSelectAllPermissions(roles.isSelectAll());
			rolesVO.setActive(roles.isActive());
		}
		return rolesVO;
	}
}
