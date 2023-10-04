package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.ReportsCommonPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.ReportsCommonPermission;
import com.nous.rollingrevenue.repository.ReportsCommonPermissionRepository;
import com.nous.rollingrevenue.service.ReportsCommonPermissionService;
import com.nous.rollingrevenue.vo.ReportsCommonPermissionVO;

@Service
@Transactional(readOnly = true)
public class ReportsCommonPermissionServiceImpl implements ReportsCommonPermissionService {

	@Autowired
	private ReportsCommonPermissionRepository reportsCommonPermissionRepository;

	@Override
	@Transactional
	public void saveReportsCommonPermission(ReportsCommonPermissionVO reportsCommonPermissionVO) {
//		reportsCommonPermissionRepository.save(ReportsCommonPermissionConverter
//				.convertReportsCommonPermissionVOToReportsCommonPermission(reportsCommonPermissionVO));
	}

	@Override
	@Transactional
	public void updateReportsCommonPermissionById(Long reportsCommonPermissionId,
			ReportsCommonPermissionVO reportsCommonPermissionVO) {

		ReportsCommonPermission reportsCommonPermission = reportsCommonPermissionRepository
				.findById(reportsCommonPermissionId).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + reportsCommonPermissionId));
//		reportsCommonPermission.setIsViewAllDataRequired(reportsCommonPermissionVO.getIsViewAllDataRequired());
//		reportsCommonPermission.setIsViewRequired(reportsCommonPermissionVO.getIsViewRequired());
//		reportsCommonPermission.setIsSaveReportViewRequired(reportsCommonPermissionVO.getIsSaveReportViewRequired());
//		reportsCommonPermission.setIsPrintRequired(reportsCommonPermissionVO.getIsPrintRequired());
//		reportsCommonPermission.setIsMailRequired(reportsCommonPermissionVO.getIsMailRequired());
//		reportsCommonPermission.setIsExportRequired(reportsCommonPermissionVO.getIsExportRequired());
		reportsCommonPermissionRepository.save(reportsCommonPermission);
	}

	@Override
	@Transactional
	public void deleteReportsCommonPermissionById(Long reportsCommonPermissionId) {
		reportsCommonPermissionRepository.findById(reportsCommonPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + reportsCommonPermissionId));
		reportsCommonPermissionRepository.deleteById(reportsCommonPermissionId);
	}

	@Override
	public List<ReportsCommonPermissionVO> getAllReportsCommonPermissions() {
		List<ReportsCommonPermissionVO> reportsCommonPermissionVO = new ArrayList<>();
//		reportsCommonPermissionRepository.findAll().stream()
//				.forEach(reportsCommonPermission -> reportsCommonPermissionVO.add(ReportsCommonPermissionConverter
//						.convertReportsCommonPermissionToReportsCommonPermissionVO(reportsCommonPermission)));
		return reportsCommonPermissionVO;
	}

	@Override
	public ReportsCommonPermissionVO getReportsCommonPermissionById(Long reportsCommonPermissionId) {
		ReportsCommonPermission reportsCommonPermission = reportsCommonPermissionRepository
				.findById(reportsCommonPermissionId).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + reportsCommonPermissionId));
//		return ReportsCommonPermissionConverter
//				.convertReportsCommonPermissionToReportsCommonPermissionVO(reportsCommonPermission);
		return null;
	}

}