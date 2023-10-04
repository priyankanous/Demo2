package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.RollingRevenueEntryPermission;
import com.nous.rollingrevenue.vo.RollingRevenueEntryPermissionVO;

@Component
public class RollingRevenueEntryPermissionConverter {

	/**
	 * Convert RollingRevenueEntryPermissionVO to RollingRevenueEntryPermission
	 * 
	 * @param RollingRevenueEntryPermissionVO
	 * @return RollingRevenueEntryPermission
	 */

//	public static RollingRevenueEntryPermission convertRollingRevenueEntryPermissionVOToRollingRevenueEntryPermission(
//			RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO) {
//		RollingRevenueEntryPermission rollingRevenueEntryPermission = new RollingRevenueEntryPermission();
//		if (rollingRevenueEntryPermissionVO != null) {
//			rollingRevenueEntryPermission.setRollingrevenueEntryPermissionId(rollingRevenueEntryPermissionVO.getRollingrevenueEntryPermissionId());
//			rollingRevenueEntryPermission
//					.setIsAddRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsAddRevenueEntryRequired());
//			rollingRevenueEntryPermission
//					.setIsAllEntriesRequired(rollingRevenueEntryPermissionVO.getIsAllEntriesRequired());
//			rollingRevenueEntryPermission
//					.setIsCopyRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsCopyRevenueEntryRequired());
//			rollingRevenueEntryPermission
//					.setIsDeleteRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsDeleteRevenueEntryRequired());
//			rollingRevenueEntryPermission
//					.setIsEditRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsEditRevenueEntryRequired());
//			rollingRevenueEntryPermission.setIsExportRequired(rollingRevenueEntryPermissionVO.getIsExportRequired());
//			rollingRevenueEntryPermission
//					.setIsOnlyIfCreatorRequired(rollingRevenueEntryPermissionVO.getIsOnlyIfCreatorRequired());
//			rollingRevenueEntryPermission.setIsPrintRequired(rollingRevenueEntryPermissionVO.getIsPrintRequired());
//			rollingRevenueEntryPermission
//					.setIsSubmitRevenueEntryRequired(rollingRevenueEntryPermissionVO.getIsSubmitRevenueEntryRequired());
//			rollingRevenueEntryPermission
//					.setIsViewAllEntriesRequired(rollingRevenueEntryPermissionVO.getIsViewAllEntriesRequired());
//		}
//		return rollingRevenueEntryPermission;
//	}
//
//	/**
//	 * Convert RollingRevenueEntryPermission to RollingRevenueEntryPermissionVO
//	 * 
//	 * @param RollingRevenueEntryPermission
//	 * @return RollingRevenueEntryPermissionVO
//	 */
//
//	public static RollingRevenueEntryPermissionVO convertRollingRevenueEntryPermissionToRollingRevenueEntryPermissionVO(
//			RollingRevenueEntryPermission rollingRevenueEntryPermission) {
//		RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO = new RollingRevenueEntryPermissionVO();
//		if (rollingRevenueEntryPermission != null) {
//			rollingRevenueEntryPermissionVO.setRollingrevenueEntryPermissionId(rollingRevenueEntryPermission.getRollingrevenueEntryPermissionId());
//			rollingRevenueEntryPermissionVO
//					.setIsAddRevenueEntryRequired(rollingRevenueEntryPermission.getIsAddRevenueEntryRequired());
//			rollingRevenueEntryPermissionVO
//					.setIsAllEntriesRequired(rollingRevenueEntryPermission.getIsAllEntriesRequired());
//			rollingRevenueEntryPermissionVO
//					.setIsCopyRevenueEntryRequired(rollingRevenueEntryPermission.getIsCopyRevenueEntryRequired());
//			rollingRevenueEntryPermissionVO
//					.setIsDeleteRevenueEntryRequired(rollingRevenueEntryPermission.getIsDeleteRevenueEntryRequired());
//			rollingRevenueEntryPermissionVO
//					.setIsEditRevenueEntryRequired(rollingRevenueEntryPermission.getIsEditRevenueEntryRequired());
//			rollingRevenueEntryPermissionVO.setIsExportRequired(rollingRevenueEntryPermission.getIsExportRequired());
//			rollingRevenueEntryPermissionVO
//					.setIsOnlyIfCreatorRequired(rollingRevenueEntryPermission.getIsOnlyIfCreatorRequired());
//			rollingRevenueEntryPermissionVO.setIsPrintRequired(rollingRevenueEntryPermission.getIsPrintRequired());
//			rollingRevenueEntryPermissionVO
//					.setIsSubmitRevenueEntryRequired(rollingRevenueEntryPermission.getIsSubmitRevenueEntryRequired());
//			rollingRevenueEntryPermissionVO
//					.setIsViewAllEntriesRequired(rollingRevenueEntryPermission.getIsViewAllEntriesRequired());
//			rollingRevenueEntryPermissionVO.setActive(rollingRevenueEntryPermission.isActive());
//		}
//		return rollingRevenueEntryPermissionVO;
//	}
}
