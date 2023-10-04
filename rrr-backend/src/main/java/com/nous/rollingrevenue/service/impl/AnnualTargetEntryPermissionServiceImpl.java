package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.AnnualTargetEntryPermission;
import com.nous.rollingrevenue.repository.AnnualTargetEntryPermissionRepository;
import com.nous.rollingrevenue.service.AnnualTargetEntryPermissionService;
import com.nous.rollingrevenue.vo.AnnualTargetEntryPermissionVO;

@Service
@Transactional(readOnly = true)
public class AnnualTargetEntryPermissionServiceImpl implements AnnualTargetEntryPermissionService {

	@Autowired
	private AnnualTargetEntryPermissionRepository annualTargetEntryPermissionRepository;

	@Override
	public List<AnnualTargetEntryPermissionVO> getAllAnnualTargetEntryPermission() {
		List<AnnualTargetEntryPermissionVO> annualTargetEntryPermissionVO = new ArrayList<>();
//		annualTargetEntryPermissionRepository.findAll().stream()
//				.forEach(annualTargetEntryPermission -> annualTargetEntryPermissionVO.add(AnnualTargetEntryPermissionConverter.convertAnnualTargetEntryPermissionToAnnualTargetEntryPermissionVO(annualTargetEntryPermission)));
		return annualTargetEntryPermissionVO;
	}

	@Override
	@Transactional
	public void saveAnnualTargetEntryPermission(AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO) {
//		annualTargetEntryPermissionRepository.save(AnnualTargetEntryPermissionConverter.convertAnnualTargetEntryPermissionVOToAnnualTargetEntryPermission(annualTargetEntryPermissionVO));
	}

	@Override
	@Transactional
	public void deleteAnnualTargetEntryPermissionById(Long annualTargetEntryPermissionId) {
		annualTargetEntryPermissionRepository.findById(annualTargetEntryPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryPermissionId));
		annualTargetEntryPermissionRepository.deleteById(annualTargetEntryPermissionId);
	}

	@Override
	public AnnualTargetEntryPermissionVO getAnnualTargetEntryPermissionById(Long annualTargetEntryPermissionId) {
		AnnualTargetEntryPermission annualTargetEntryPermission = annualTargetEntryPermissionRepository
				.findById(annualTargetEntryPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryPermissionId));
//		return AnnualTargetEntryPermissionConverter.convertAnnualTargetEntryPermissionToAnnualTargetEntryPermissionVO(annualTargetEntryPermission);
		return null;
	}

	@Override
	@Transactional
	public void updateAnnualTargetEntryPermission(Long annualTargetEntryPermissionId,
			AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO) {
		AnnualTargetEntryPermission annualTargetEntryPermission = annualTargetEntryPermissionRepository
				.findById(annualTargetEntryPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryPermissionId));
//		annualTargetEntryPermission
//				.setIsAddAnnualTargetEntryRequired(annualTargetEntryPermissionVO.getIsAddAnnualTargetEntryRequired());
		annualTargetEntryPermissionRepository.save(annualTargetEntryPermission);
	}

}
