package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.DashboardPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.DashboardPermission;
import com.nous.rollingrevenue.repository.DashboardPermissionRepository;
import com.nous.rollingrevenue.service.DashboardPermissionService;
import com.nous.rollingrevenue.vo.DashboardPermissionVO;

@Service
@Transactional(readOnly = true)
public class DashboardPermissionServiceImpl implements DashboardPermissionService {

	@Autowired
	private DashboardPermissionRepository dashboardPermissionRepository;

	@Override
	public List<DashboardPermissionVO> getAllDashboardPermission() {
		List<DashboardPermissionVO> dashboardPermissionsVO = new ArrayList<>();
		dashboardPermissionRepository.findAll().stream().forEach(dashboardPermission -> dashboardPermissionsVO.add(
				DashboardPermissionConverter.convertDashboardPermissionToDashboardPermissionVO(dashboardPermission)));
		return dashboardPermissionsVO;
	}

	@Override
	@Transactional
	public void saveDashboardPermission(DashboardPermissionVO dashboardPermissionVO) {
		dashboardPermissionRepository.save(
				DashboardPermissionConverter.convertDashboardPermissionVOToDashboardPermission(dashboardPermissionVO));

	}

	@Override
	@Transactional
	public void deleteDashboardPermissionById(Long dashboardPermissionId) {
		dashboardPermissionRepository.findById(dashboardPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + dashboardPermissionId));
		dashboardPermissionRepository.deleteById(dashboardPermissionId);

	}

	@Override
	public DashboardPermissionVO getDashboardPermissionById(Long dashboardPermissionId) {
		DashboardPermission dashboardPermission = dashboardPermissionRepository.findById(dashboardPermissionId)
				.orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + dashboardPermissionId));
		return DashboardPermissionConverter.convertDashboardPermissionToDashboardPermissionVO(dashboardPermission);
	}

	@Override
	@Transactional
	public void updateDashboardPermission(Long dashboardPermissionId, DashboardPermissionVO dashboardPermissionVO) {
		DashboardPermission dashboardPermission = dashboardPermissionRepository.findById(dashboardPermissionId)
				.orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + dashboardPermissionId));
		dashboardPermission.setIsEditDashboardRequired(dashboardPermissionVO.getIsEditDashboardRequired());
		dashboardPermission.setIsReadDashboardRequired(dashboardPermissionVO.getIsReadDashboardRequired());
		dashboardPermissionRepository.save(dashboardPermission);
	}
}
