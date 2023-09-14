package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.NotificationConfigurationPermission;
import com.nous.rollingrevenue.vo.NotificationConfigurationPermissionVO;

@Component
public class NotificationConfigurationPermissionConverter {

	/**
	 * Convert NotificationConfigurationPermissionVO to
	 * NotificationConfigurationPermission
	 * 
	 * @param NotificationConfigurationPermissionVO
	 * @return NotificationConfigurationPermission
	 */
	public static NotificationConfigurationPermission convertNotificationConfigurationPermissionVOToNotificationConfigurationPermission(
			NotificationConfigurationPermissionVO notificationConfigurationPermissionVO) {
		com.nous.rollingrevenue.model.NotificationConfigurationPermission notificationConfigurationPermission = new NotificationConfigurationPermission();
		if (notificationConfigurationPermissionVO != null) {
			notificationConfigurationPermission.setNotificationConfigurationPermissionId(
					notificationConfigurationPermissionVO.getNotificationConfigurationPermissionId());
			notificationConfigurationPermission.setIsCreateNewNotificationEmailTemplateRequired(
					notificationConfigurationPermissionVO.getIsCreateNewNotificationEmailTemplateRequired());
			notificationConfigurationPermission
					.setIsViewRequired(notificationConfigurationPermissionVO.getIsViewRequired());
			notificationConfigurationPermission
					.setIsCopyRequired(notificationConfigurationPermissionVO.getIsCopyRequired());
			notificationConfigurationPermission
					.setIsEditRequired(notificationConfigurationPermissionVO.getIsEditRequired());
			notificationConfigurationPermission.setIsDeleteOrDeactivateRequired(
					notificationConfigurationPermissionVO.getIsDeleteOrDeactivateRequired());
			notificationConfigurationPermission.setIsAssignRecipientsRequired(
					notificationConfigurationPermissionVO.getIsAssignRecipientsRequired());

		}
		return notificationConfigurationPermission;
	}

	/**
	 * Convert NotificationConfigurationPermission to
	 * NotificationConfigurationPermissionVO
	 * 
	 * @param NotificationConfigurationPermission
	 * @return NotificationConfigurationPermissionVO
	 */

	public static NotificationConfigurationPermissionVO convertNotificationConfigurationPermissionToNotificationConfigurationPermissionVO(
			NotificationConfigurationPermission notificationConfigurationPermission) {
		NotificationConfigurationPermissionVO notificationConfigurationPermissionVO = new NotificationConfigurationPermissionVO();
		if (notificationConfigurationPermission != null) {
			notificationConfigurationPermissionVO.setNotificationConfigurationPermissionId(
					notificationConfigurationPermission.getNotificationConfigurationPermissionId());
			notificationConfigurationPermissionVO.setIsCreateNewNotificationEmailTemplateRequired(
					notificationConfigurationPermission.getIsCreateNewNotificationEmailTemplateRequired());
			notificationConfigurationPermissionVO
					.setIsViewRequired(notificationConfigurationPermission.getIsViewRequired());
			notificationConfigurationPermissionVO
					.setIsCopyRequired(notificationConfigurationPermission.getIsCopyRequired());
			notificationConfigurationPermissionVO
					.setIsEditRequired(notificationConfigurationPermission.getIsEditRequired());
			notificationConfigurationPermissionVO.setIsDeleteOrDeactivateRequired(
					notificationConfigurationPermission.getIsDeleteOrDeactivateRequired());
			notificationConfigurationPermissionVO
					.setIsAssignRecipientsRequired(notificationConfigurationPermission.getIsAssignRecipientsRequired());
			notificationConfigurationPermissionVO.setActive(notificationConfigurationPermission.isActive());

		}
		return notificationConfigurationPermissionVO;
	}

}
