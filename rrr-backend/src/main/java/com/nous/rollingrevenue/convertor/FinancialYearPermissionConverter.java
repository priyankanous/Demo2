package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.FinancialYearPermission;
import com.nous.rollingrevenue.vo.FinancialYearPermissionVO;

@Component
public class FinancialYearPermissionConverter {

	/**
	 * Convert FinancialYearPermissionVO to FinancialYearPermission
	 * 
	 * @param FinancialYearVO
	 * @return FinancialYearPermission
	 */
//	public static FinancialYearPermission convertFinancialYearPermissionVOToFinancialYearPermission(
//			FinancialYearPermissionVO financialYearPermissionVO) {
//		FinancialYearPermission financialYearPermission = new FinancialYearPermission();
//		if (financialYearPermissionVO != null) {
//			financialYearPermission
//					.setFinancialYearPermissionId(financialYearPermissionVO.getFinancialYearPermissionId());
//			financialYearPermission.setIsCreateRequired(financialYearPermissionVO.getIsCreateRequired());
//			financialYearPermission
//					.setIsActivateOrDeactivateRequired(financialYearPermissionVO.getIsActivateOrDeactivateRequired());
//		}
//		return financialYearPermission;
//	}
//
//	/**
//	 * Convert FinancialYearPermission to FinancialYearPermissionVO
//	 * 
//	 * @param FinancialYearPermission
//	 * @return FinancialYearPermissionVO
//	 */
//	public static FinancialYearPermissionVO convertFinancialYearPermissionToFinancialYearPermissionVO(
//			FinancialYearPermission financialYearPermission) {
//		FinancialYearPermissionVO financialYearPermissionVO = new FinancialYearPermissionVO();
//		if (financialYearPermission != null) {
//			financialYearPermissionVO
//					.setFinancialYearPermissionId(financialYearPermission.getFinancialYearPermissionId());
//			financialYearPermissionVO.setIsCreateRequired(financialYearPermission.getIsCreateRequired());
//			financialYearPermissionVO
//					.setIsActivateOrDeactivateRequired(financialYearPermission.getIsActivateOrDeactivateRequired());
//			financialYearPermissionVO.setActive(financialYearPermission.isActive());
//		}
//		return financialYearPermissionVO;
//	}
}
