package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.RollingRevenueEntryPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.RollingRevenueEntryPermission;
import com.nous.rollingrevenue.repository.RollingRevenueEntryPermissionRepository;
import com.nous.rollingrevenue.service.RollingRevenueEntryPermissionService;
import com.nous.rollingrevenue.vo.RollingRevenueEntryPermissionVO;

@Service
@Transactional(readOnly = true)
public class RollingRevenueEntryPermissionServiceImpl implements RollingRevenueEntryPermissionService {

	@Autowired
	private RollingRevenueEntryPermissionRepository rollingRevenueEntryPermissionRepository;

	@Override
	@Transactional
	public void saveRollingRevenueEntryPermission(RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO) {
//		rollingRevenueEntryPermissionRepository.save(RollingRevenueEntryPermissionConverter
//				.convertRollingRevenueEntryPermissionVOToRollingRevenueEntryPermission(
//						rollingRevenueEntryPermissionVO));
	}

	@Override
	@Transactional
	public void updateRollingRevenueEntryPermissionById(Long rollingrevenueEntryPermissionId,
			RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO) {
		RollingRevenueEntryPermission rollingRevenueEntryPermission = rollingRevenueEntryPermissionRepository
				.findById(rollingrevenueEntryPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + rollingrevenueEntryPermissionId));
		rollingRevenueEntryPermission
				.setIsAddRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsAddRevenueEntryRequired());
//		rollingRevenueEntryPermission
//				.setIsAllEntriesRequired(rollingRevenueEntryPermissionVO.getIsAllEntriesRequired());
		rollingRevenueEntryPermission
				.setIsCopyRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsCopyRevenueEntryRequired());
		rollingRevenueEntryPermission
				.setIsDeleteRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsDeleteRevenueEntryRequired());
		rollingRevenueEntryPermission
				.setIsEditRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsEditRevenueEntryRequired());
		rollingRevenueEntryPermission.setIsExportRequired(rollingRevenueEntryPermissionVO.getIsExportRequired());
//		rollingRevenueEntryPermission
//				.setIsOnlyIfCreatorRequired(rollingRevenueEntryPermissionVO.getIsOnlyIfCreatorRequired());
//		rollingRevenueEntryPermission.setIsPrintRequired(rollingRevenueEntryPermissionVO.getIsPrintRequired());
		rollingRevenueEntryPermission
				.setIsSubmitRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsSubmitRevenueEntryRequired());
		rollingRevenueEntryPermission
				.setIsViewAllEntriesRequired(rollingRevenueEntryPermissionVO.getIsViewAllEntriesRequired());
		rollingRevenueEntryPermissionRepository.save(rollingRevenueEntryPermission);
	}

	@Override
	@Transactional
	public void deleteRollingRevenueEntryPermissionById(Long rollingrevenueEntryPermissionId) {
		rollingRevenueEntryPermissionRepository.findById(rollingrevenueEntryPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + rollingrevenueEntryPermissionId));
		rollingRevenueEntryPermissionRepository.deleteById(rollingrevenueEntryPermissionId);
	}

	@Override
	public List<RollingRevenueEntryPermissionVO> getAllRollingRevenueEntryPermissions() {
		List<RollingRevenueEntryPermissionVO> rollingRevenueEntryPermissionVO = new ArrayList<>();
//		rollingRevenueEntryPermissionRepository.findAll().stream()
//				.forEach(rollingrevenueEntryPermission -> rollingRevenueEntryPermissionVO
//						.add(RollingRevenueEntryPermissionConverter
//								.convertRollingRevenueEntryPermissionToRollingRevenueEntryPermissionVO(
//										rollingrevenueEntryPermission)));
		return rollingRevenueEntryPermissionVO;
	}

	@Override
	public RollingRevenueEntryPermissionVO getRollingRevenueEntryPermissionById(Long rollingrevenueEntryPermissionId) {
		RollingRevenueEntryPermission rollingrevenueEntryPermission = rollingRevenueEntryPermissionRepository
				.findById(rollingrevenueEntryPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + rollingrevenueEntryPermissionId));
//		return RollingRevenueEntryPermissionConverter
//				.convertRollingRevenueEntryPermissionToRollingRevenueEntryPermissionVO(rollingrevenueEntryPermission);
		return null;
	}

}
