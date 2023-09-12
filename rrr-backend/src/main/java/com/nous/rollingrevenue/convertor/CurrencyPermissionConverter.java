package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.CurrencyPermission;
import com.nous.rollingrevenue.vo.CurrencyPermissionVO;

@Component
public class CurrencyPermissionConverter {

	public static CurrencyPermission convertCurrencyPermissionVOToCurrencyPermission(
			CurrencyPermissionVO currencyPermissionVO) {
		CurrencyPermission currencyPermission = new CurrencyPermission();
		if (currencyPermissionVO != null) {
			currencyPermission.setCurrencyPermissionId(currencyPermissionVO.getCurrencyPermissionId());
			currencyPermission.setViewRequired(currencyPermissionVO.getIsViewRequired());
			currencyPermission.setAddRequired(currencyPermissionVO.getIsAddRequired());
			currencyPermission.setDeleteOrDeactiveRequired(currencyPermissionVO.getIsDeleteOrDeactiveRequired());
			currencyPermission.setSetConversionForFyRequired(currencyPermissionVO.getIsSetConversionForFyRequired());
		}
		return currencyPermission;
	}

	public static CurrencyPermissionVO convertCurrencyPermissionToCurrencyPermissionVO(
			CurrencyPermission currencyPermission) {
		CurrencyPermissionVO currencyPermissionVO = new CurrencyPermissionVO();
		if (currencyPermission != null) {
			currencyPermissionVO.setCurrencyPermissionId(currencyPermission.getCurrencyPermissionId());
			currencyPermissionVO.setViewRequired(currencyPermission.getIsViewRequired());
			currencyPermissionVO.setAddRequired(currencyPermission.getIsAddRequired());
			currencyPermissionVO.setDeleteOrDeactiveRequired(currencyPermission.getIsDeleteOrDeactiveRequired());
			currencyPermissionVO.setSetConversionForFyRequired(currencyPermission.getIsSetConversionForFyRequired());
			currencyPermissionVO.setActive(currencyPermission.isActive());
		}
		return currencyPermissionVO;
	}

}
