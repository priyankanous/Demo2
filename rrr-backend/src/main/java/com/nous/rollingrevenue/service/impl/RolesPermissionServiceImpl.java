package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.RolesPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.RolesPermission;
import com.nous.rollingrevenue.repository.RolesPermissionRepository;
import com.nous.rollingrevenue.service.RolesPermissionService;
import com.nous.rollingrevenue.vo.RolesPermissionVO;

@Service
@Transactional(readOnly = true)
public class RolesPermissionServiceImpl implements RolesPermissionService {

	@Autowired
	private RolesPermissionRepository rolesPermissionRepository;

	@Override
	public List<RolesPermissionVO> getAllRolesPermission() {
		List<RolesPermissionVO> rolesPermissionVO = new ArrayList<>();
//		rolesPermissionRepository.findAll().stream().forEach(rolesPermission -> rolesPermissionVO
//				.add(RolesPermissionConverter.convertRolesPermissionToRolesPermissionVO(rolesPermission)));
		return rolesPermissionVO;
	}

	@Override
	@Transactional
	public void saveRolesPermission(RolesPermissionVO rolesPermissionVO) {
//		rolesPermissionRepository
//				.save(RolesPermissionConverter.convertRolesPermissionVOToRolesPermission(rolesPermissionVO));
	}

	@Override
	@Transactional
	public void deleteRolesPermissionById(Long rolesPermissionId) {
		rolesPermissionRepository.findById(rolesPermissionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + rolesPermissionId));
		rolesPermissionRepository.deleteById(rolesPermissionId);
	}

	@Override
	public RolesPermissionVO getRolesPermissionById(Long rolesPermissionId) {
		RolesPermission rolesPermission = rolesPermissionRepository.findById(rolesPermissionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + rolesPermissionId));
//		return RolesPermissionConverter.convertRolesPermissionToRolesPermissionVO(rolesPermission);
		return null;
	}

	@Override
	@Transactional
	public void updateRolesPermission(Long rolesPermissionId, RolesPermissionVO rolesPermissionVO) {
		RolesPermission rolesPermission = rolesPermissionRepository.findById(rolesPermissionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + rolesPermissionId));
		rolesPermission.setIsCreateRoleRequired(rolesPermissionVO.getIsCreateRoleRequired());
//		rolesPermission.setIsCopyRoleRequired(rolesPermissionVO.getIsCopyRoleRequired());
//		rolesPermission.setIsEditRoleRequired(rolesPermissionVO.getIsEditRoleRequired());
//		rolesPermission.setIsDeleteOrDeactivateRequired(rolesPermissionVO.getIsDeleteOrDeactivateRequired());
		rolesPermissionRepository.save(rolesPermission);
	}

}