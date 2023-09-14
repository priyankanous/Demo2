package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.FinancialYearPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.FinancialYearPermission;
import com.nous.rollingrevenue.repository.FinancialYearPermissionRepository;
import com.nous.rollingrevenue.service.FinancialYearPermissionService;
import com.nous.rollingrevenue.vo.FinancialYearPermissionVO;

@Service
@Transactional(readOnly = true)
public class FinancialYearPermissionServiceImpl implements FinancialYearPermissionService {

	@Autowired
	private FinancialYearPermissionRepository financialYearPermissionRepository;

	@Override
	@Transactional
	public void saveFinancialYearPermission(FinancialYearPermissionVO financialYearPermissionVO) {
		financialYearPermissionRepository.save(FinancialYearPermissionConverter
				.convertFinancialYearPermissionVOToFinancialYearPermission(financialYearPermissionVO));
	}

	@Override
	@Transactional
	public void updateFinancialYearPermissionById(Long financialYearPermissionId,
			FinancialYearPermissionVO financialYearPermissionVO) {
		FinancialYearPermission financialYearPermission = financialYearPermissionRepository
				.findById(financialYearPermissionId).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearPermissionId));
		financialYearPermission.setFinancialYearPermissionId(financialYearPermissionVO.getFinancialYearPermissionId());
		financialYearPermission.setIsCreateRequired(financialYearPermissionVO.getIsCreateRequired());
		financialYearPermission
				.setIsActivateOrDeactivateRequired(financialYearPermissionVO.getIsActivateOrDeactivateRequired());
	}

	@Override
	@Transactional
	public void deleteFinancialYearPermissionById(Long financialYearPermissionId) {
		financialYearPermissionRepository.findById(financialYearPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearPermissionId));
		financialYearPermissionRepository.deleteById(financialYearPermissionId);
	}

	@Override
	public List<FinancialYearPermissionVO> getFinancialYearPermissions() {
		List<FinancialYearPermissionVO> financialYearPermissionVO = new ArrayList<>();
		financialYearPermissionRepository.findAll().stream()
				.forEach(financialYearPermission -> financialYearPermissionVO.add(FinancialYearPermissionConverter
						.convertFinancialYearPermissionToFinancialYearPermissionVO(financialYearPermission)));
		return financialYearPermissionVO;
	}

	@Override
	public FinancialYearPermissionVO getFinancialYearPermissionById(Long financialYearPermissionId) {
		FinancialYearPermission financialYearPermission = financialYearPermissionRepository
				.findById(financialYearPermissionId).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearPermissionId));
		return FinancialYearPermissionConverter
				.convertFinancialYearPermissionToFinancialYearPermissionVO(financialYearPermission);
	}

}
