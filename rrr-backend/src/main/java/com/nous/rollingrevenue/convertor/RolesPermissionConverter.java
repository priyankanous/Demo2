package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.RolesPermission;
import com.nous.rollingrevenue.vo.RolesPermissionVO;

@Component
public class RolesPermissionConverter {

	/**
	 * Convert RolesPermissionVO to RolesPermission
	 * 
	 * @param RolesPermissionVO
	 * @return RolesPermission
	 */
	public static RolesPermission convertRolesPermissionVOToRolesPermission(RolesPermissionVO rolesPermissionVO) {
		RolesPermission rolesPermission = new RolesPermission();
		if (rolesPermissionVO != null) {
			rolesPermission.setRolesPermissionId(rolesPermissionVO.getRolesPermissionId());
			rolesPermission.setIsCreateRoleRequired(rolesPermissionVO.getIsCreateRoleRequired());
			rolesPermission.setIsCopyRoleRequired(rolesPermissionVO.getIsCopyRoleRequired());
			rolesPermission.setIsEditRoleRequired(rolesPermissionVO.getIsEditRoleRequired());
			rolesPermission.setIsDeleteOrDeactivateRequired(rolesPermissionVO.getIsDeleteOrDeactivateRequired());
		}
		return rolesPermission;
	}

	/**
	 * Convert RolesPermission to RolesPermissionVO
	 * 
	 * @param RolesPermission
	 * @return RolesPermissionVO
	 */
	public static RolesPermissionVO convertRolesPermissionToRolesPermissionVO(RolesPermission rolesPermission) {
		RolesPermissionVO rolesPermissionVO = new RolesPermissionVO();
		if (rolesPermission != null) {
			rolesPermissionVO.setRolesPermissionId(rolesPermission.getRolesPermissionId());
			rolesPermissionVO.setIsCreateRoleRequired(rolesPermission.getIsCreateRoleRequired());
			rolesPermissionVO.setIsCopyRoleRequired(rolesPermission.getIsCopyRoleRequired());
			rolesPermissionVO.setIsEditRoleRequired(rolesPermission.getIsEditRoleRequired());
			rolesPermissionVO.setIsDeleteOrDeactivateRequired(rolesPermission.getIsDeleteOrDeactivateRequired());
			rolesPermissionVO.setActive(rolesPermission.isActive());
		}
		return rolesPermissionVO;
	}

}