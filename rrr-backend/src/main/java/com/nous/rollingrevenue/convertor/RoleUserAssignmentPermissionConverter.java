package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.RoleUserAssignmentPermission;
import com.nous.rollingrevenue.vo.RoleUserAssignmentPermissionVO;

@Component
public class RoleUserAssignmentPermissionConverter {

//	public static RoleUserAssignmentPermission convertRoleUserAssignmentPermissionVOToRoleUserAssignmentPermission(
//			RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO) {
//		RoleUserAssignmentPermission roleUserAssignmentPermission = new RoleUserAssignmentPermission();
//		if (roleUserAssignmentPermissionVO != null) {
//			roleUserAssignmentPermission.setRoleUserAssignmentPermissionId(
//					roleUserAssignmentPermissionVO.getRoleUserAssignmentPermissionId());
//			roleUserAssignmentPermission.setAssignOrModifyUsersToRolesRequired(
//					roleUserAssignmentPermissionVO.getIsAssignOrModifyUsersToRolesRequired());
//			roleUserAssignmentPermission.setActivateOrDeactivateNotificationRequired(
//					roleUserAssignmentPermissionVO.getIsActivateOrDeactivateNotificationRequired());
//		}
//		return roleUserAssignmentPermission;
//	}
//
//	public static RoleUserAssignmentPermissionVO convertRoleUserAssignmentPermissionToRoleUserAssignmentPermissionVO(
//			RoleUserAssignmentPermission roleUserAssignmentPermission) {
//		RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO = new RoleUserAssignmentPermissionVO();
//		if (roleUserAssignmentPermission != null) {
//			roleUserAssignmentPermissionVO.setRoleUserAssignmentPermissionId(
//					roleUserAssignmentPermission.getRoleUserAssignmentPermissionId());
//			roleUserAssignmentPermissionVO.setAssignOrModifyUsersToRolesRequired(
//					roleUserAssignmentPermission.getIsAssignOrModifyUsersToRolesRequired());
//			roleUserAssignmentPermissionVO.setActivateOrDeactivateNotificationRequired(
//					roleUserAssignmentPermission.getIsActivateOrDeactivateNotificationRequired());
//			roleUserAssignmentPermissionVO.setActive(roleUserAssignmentPermission.isActive());
//		}
//		return roleUserAssignmentPermissionVO;
//	}

}
