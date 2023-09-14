package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.NotificationsPermission;
import com.nous.rollingrevenue.vo.NotificationsPermissionVO;

@Component
public class NotificationsPermissionConverter {

	/**
	 * Convert NotificationsPermissionVO to NotificationsPermission
	 * 
	 * @param NotificationsPermissionVO
	 * @return NotificationsPermission
	 */
	public static NotificationsPermission convertNotificationsPermissionVOToNotificationsPermission(
			NotificationsPermissionVO notificationsPermissionVO) {
		com.nous.rollingrevenue.model.NotificationsPermission notificationsPermission = new NotificationsPermission();
		if (notificationsPermissionVO != null) {
			notificationsPermission
					.setNotificationsPermissionId(notificationsPermissionVO.getNotificationsPermissionId());
			notificationsPermission.setIsAssignOrModifyCcRecipientsRequired(
					notificationsPermissionVO.getIsAssignOrModifyCcRecipientsRequired());
			notificationsPermission
					.setIsActivateOrDeactivateRequired(notificationsPermissionVO.getIsActivateOrDeactivateRequired());
		}
		return notificationsPermission;
	}

	/**
	 * Convert NotificationsPermission to NotificationsPermissionVO
	 * 
	 * @param NotificationsPermission
	 * @return NotificationsPermissionVO
	 */
	public static NotificationsPermissionVO convertNotificationsPermissionToNotificationsPermissionVO(
			NotificationsPermission notificationsPermission) {
		NotificationsPermissionVO notificationsPermissionVO = new NotificationsPermissionVO();
		if (notificationsPermission != null) {
			notificationsPermissionVO
					.setNotificationsPermissionId(notificationsPermission.getNotificationsPermissionId());
			notificationsPermissionVO.setIsAssignOrModifyCcRecipientsRequired(
					notificationsPermission.getIsAssignOrModifyCcRecipientsRequired());
			notificationsPermissionVO
					.setIsActivateOrDeactivateRequired(notificationsPermission.getIsActivateOrDeactivateRequired());
			notificationsPermissionVO.setActive(notificationsPermission.isActive());
		}
		return notificationsPermissionVO;
	}

}
