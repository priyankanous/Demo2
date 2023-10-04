package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.CurrencyPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.CurrencyPermission;
import com.nous.rollingrevenue.repository.CurrencyPermissionRepository;
import com.nous.rollingrevenue.service.CurrencyPermissionService;
import com.nous.rollingrevenue.vo.CurrencyPermissionVO;

@Service
@Transactional(readOnly = true)
public class CurrencyPermissionServiceImpl implements CurrencyPermissionService {

	@Autowired
	private CurrencyPermissionRepository currencyPermissionRepository;

	@Override
	public List<CurrencyPermissionVO> getAllCurrencyPermission() {
		List<CurrencyPermissionVO> currencyPermissionsVO = new ArrayList<>();
//		currencyPermissionRepository.findAll().stream().forEach(currencyPermission -> currencyPermissionsVO
//				.add(CurrencyPermissionConverter.convertCurrencyPermissionToCurrencyPermissionVO(currencyPermission)));
		return currencyPermissionsVO;
	}

	@Override
	@Transactional
	public void saveCurrencyPermission(CurrencyPermissionVO currencyPermissionVO) {
//		currencyPermissionRepository.save(
//				CurrencyPermissionConverter.convertCurrencyPermissionVOToCurrencyPermission(currencyPermissionVO));
	}

	@Override
	@Transactional
	public void deleteCurrencyPermissionById(Long currencyPermissionId) {
		currencyPermissionRepository.findById(currencyPermissionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyPermissionId));
		currencyPermissionRepository.deleteById(currencyPermissionId);
	}

	@Override
	public CurrencyPermissionVO getCurrencyPermissionById(Long currencyPermissionId) {
		CurrencyPermission currencyPermission = currencyPermissionRepository.findById(currencyPermissionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyPermissionId));
//		return CurrencyPermissionConverter.convertCurrencyPermissionToCurrencyPermissionVO(currencyPermission);
		return null;
	}

	@Override
	@Transactional
	public void updateCurrencyPermission(Long currencyPermissionId, CurrencyPermissionVO currencyPermissionVO) {
		CurrencyPermission currencyPermission = currencyPermissionRepository.findById(currencyPermissionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyPermissionId));
//		currencyPermission.setViewRequired(currencyPermissionVO.getIsViewRequired());
//		currencyPermission.setAddRequired(currencyPermissionVO.getIsAddRequired());
//		currencyPermission.setDeleteOrDeactiveRequired(currencyPermissionVO.getIsDeleteOrDeactiveRequired());
//		currencyPermission.setSetConversionForFyRequired(currencyPermissionVO.getIsSetConversionForFyRequired());
		currencyPermissionRepository.save(currencyPermission);
	}

}
