package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.AnnualTargetEntryPermission;
import com.nous.rollingrevenue.vo.AnnualTargetEntryPermissionVO;

@Component
public class AnnualTargetEntryPermissionConverter {

	/**
	 * Convert AnnualTargetEntryPermissionVO to AnnualTargetEntryPermission
	 * 
	 * @param AnnualTargetEntryPermissionVO
	 * @return AnnualTargetEntryPermission
	 */

	public static AnnualTargetEntryPermission convertAnnualTargetEntryPermissionVOToAnnualTargetEntryPermission(AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO) {
		AnnualTargetEntryPermission annualTargetEntryPermission = new AnnualTargetEntryPermission();
		if (annualTargetEntryPermissionVO != null) {
			annualTargetEntryPermission.setAnnualTargetEntryPermissionId(annualTargetEntryPermissionVO.getAnnualTargetEntryPermissionId());
			annualTargetEntryPermission.setIsAddAnnualTargetEntryRequired(annualTargetEntryPermissionVO.getIsAddAnnualTargetEntryRequired());
		}
		return annualTargetEntryPermission;
	}

	/**
	 * Convert AnnualTargetEntryPermission to AnnualTargetEntryPermissionVO
	 * 
	 * @param AnnualTargetEntryPermission
	 * @return AnnualTargetEntryPermissionVO
	 */

	public static AnnualTargetEntryPermissionVO convertAnnualTargetEntryPermissionToAnnualTargetEntryPermissionVO(AnnualTargetEntryPermission annualTargetEntryPermission) {
		AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO = new AnnualTargetEntryPermissionVO();
		if (annualTargetEntryPermission != null) {
			annualTargetEntryPermissionVO.setAnnualTargetEntryPermissionId(annualTargetEntryPermission.getAnnualTargetEntryPermissionId());
			annualTargetEntryPermissionVO.setIsAddAnnualTargetEntryRequired(annualTargetEntryPermission.getIsAddAnnualTargetEntryRequired());
		}
		return annualTargetEntryPermissionVO;
	}

}
