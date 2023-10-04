package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.RoleUserAssignmentPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.RoleUserAssignmentPermission;
import com.nous.rollingrevenue.repository.RoleUserAssignmentPermissionRepository;
import com.nous.rollingrevenue.service.RoleUserAssignmentPermissionService;
import com.nous.rollingrevenue.vo.RoleUserAssignmentPermissionVO;

@Service
@Transactional(readOnly = true)
public class RoleUserAssignmentPermissionServiceImpl implements RoleUserAssignmentPermissionService {

	@Autowired
	private RoleUserAssignmentPermissionRepository roleUserAssignmentPermissionRepository;

	@Override
	public List<RoleUserAssignmentPermissionVO> getAllRoleUserAssignmentPermission() {
		List<RoleUserAssignmentPermissionVO> roleUserAssignmentPermissionsVO = new ArrayList<>();
//		roleUserAssignmentPermissionRepository.findAll().stream()
//				.forEach(roleUserAssignmentPermission -> roleUserAssignmentPermissionsVO
//						.add(RoleUserAssignmentPermissionConverter
//								.convertRoleUserAssignmentPermissionToRoleUserAssignmentPermissionVO(
//										roleUserAssignmentPermission)));
		return roleUserAssignmentPermissionsVO;
	}

	@Override
	@Transactional
	public void saveRoleUserAssignmentPermission(RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO) {
//		roleUserAssignmentPermissionRepository.save(RoleUserAssignmentPermissionConverter
//				.convertRoleUserAssignmentPermissionVOToRoleUserAssignmentPermission(roleUserAssignmentPermissionVO));
	}

	@Override
	@Transactional
	public void deleteRoleUserAssignmentPermissionById(Long roleUserAssignmentPermissionId) {
		roleUserAssignmentPermissionRepository.findById(roleUserAssignmentPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + roleUserAssignmentPermissionId));
		roleUserAssignmentPermissionRepository.deleteById(roleUserAssignmentPermissionId);

	}

	@Override
	public RoleUserAssignmentPermissionVO getRoleUserAssignmentPermissionById(Long roleUserAssignmentPermissionId) {
		RoleUserAssignmentPermission roleUserAssignmentPermission = roleUserAssignmentPermissionRepository
				.findById(roleUserAssignmentPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + roleUserAssignmentPermissionId));
//		return RoleUserAssignmentPermissionConverter
//				.convertRoleUserAssignmentPermissionToRoleUserAssignmentPermissionVO(roleUserAssignmentPermission);
		return null;
	}

	@Override
	@Transactional
	public void updateRoleUserAssignmentPermission(Long roleUserAssignmentPermissionId,
			RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO) {
		RoleUserAssignmentPermission roleUserAssignmentPermission = roleUserAssignmentPermissionRepository
				.findById(roleUserAssignmentPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + roleUserAssignmentPermissionId));
		roleUserAssignmentPermission
				.setRoleUserAssignmentPermissionId(roleUserAssignmentPermissionVO.getRoleUserAssignmentPermissionId());
//		roleUserAssignmentPermission.setAssignOrModifyUsersToRolesRequired(
//				roleUserAssignmentPermissionVO.getIsAssignOrModifyUsersToRolesRequired());
//		roleUserAssignmentPermission.setActivateOrDeactivateNotificationRequired(
//				roleUserAssignmentPermissionVO.getIsActivateOrDeactivateNotificationRequired());

		roleUserAssignmentPermissionRepository.save(roleUserAssignmentPermission);
	}
}
