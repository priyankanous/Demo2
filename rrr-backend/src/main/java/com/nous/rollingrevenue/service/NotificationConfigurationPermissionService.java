package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.NotificationConfigurationPermissionVO;

public interface NotificationConfigurationPermissionService {

	/**
	 * Add an NotificationConfigurationPermission to the database
	 * 
	 * @param NotificationConfigurationPermissionVO
	 * 
	 */
	void saveNotificationConfigurationPermission(
			NotificationConfigurationPermissionVO notificationConfigurationPermissionVO);

	/**
	 * Update an NotificationConfigurationPermission to the database by given
	 * notificationConfigurationPermissionId
	 * 
	 * @param NotificationConfigurationPermissionId, NotificationConfigurationPermissionVO
	 * 
	 */
	void updateNotificationConfigurationPermissionById(Long notificationConfigurationPermissionId,
			NotificationConfigurationPermissionVO notificationConfigurationPermissionVO);

	/**
	 * Delete an NotificationConfigurationPermission record by given
	 * notificationConfigurationPermissionId
	 * 
	 * @param notificationConfigurationPermissionId The
	 *                                              notificationConfigurationPermissionId
	 *                                              of the
	 *                                              NotificationConfigurationPermission
	 *                                              to be deleted. Throws
	 *                                              {@link RecordNotFoundException}
	 *                                              if no match is found
	 */
	void deleteNotificationConfigurationPermissionById(Long notificationConfigurationPermissionId);

	/**
	 * Get all the NotificationConfigurationPermission
	 * 
	 * @return List of all NotificationConfigurationPermissions in the database
	 */
	List<NotificationConfigurationPermissionVO> getNotificationConfigurationPermissions();

	/**
	 * Get the NotificationConfigurationPermission details by given
	 * notificationConfigurationPermissionId
	 * 
	 * @param notificationConfigurationPermissionId The
	 *                                              notificationConfigurationPermissionId
	 *                                              for retrieving the details
	 * @return The NotificationConfigurationPermission details matching the
	 *         notificationConfigurationPermissionId. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public NotificationConfigurationPermissionVO getNotificationConfigurationPermissionById(
			Long notificationConfigurationPermissionId);

}
