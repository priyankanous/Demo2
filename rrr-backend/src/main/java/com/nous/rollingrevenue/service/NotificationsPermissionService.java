package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.NotificationsPermissionVO;

public interface NotificationsPermissionService {

	/**
	 * Add an NotificationsPermission to the database
	 * 
	 * @param NotificationsPermissionVO
	 * 
	 */
	void saveNotificationsPermission(NotificationsPermissionVO notificationsPermissionVO);

	/**
	 * Update an NotificationsPermission to the database by given
	 * notificationsPermissionId
	 * 
	 * @param NotificationsPermissionId, NotificationsPermissionVO
	 * 
	 */
	void updateNotificationsPermissionById(Long notificationsPermissionId,
			NotificationsPermissionVO notificationsPermissionVO);

	/**
	 * Delete an NotificationsPermission record by given notificationsPermissionId
	 * 
	 * @param notificationsPermissionId The notificationsPermissionId of the
	 *                                  NotificationsPermission to be deleted.
	 *                                  Throws {@link RecordNotFoundException} if no
	 *                                  match is found
	 */
	void deleteNotificationsPermissionById(Long notificationsPermissionId);

	/**
	 * Get all the NotificationsPermission
	 * 
	 * @return List of all NotificationsPermissions in the database
	 */
	List<NotificationsPermissionVO> getNotificationsPermissions();

	/**
	 * Get the NotificationsPermission details by given notificationsPermissionId
	 * 
	 * @param notificationsPermissionId The notificationsPermissionId for retrieving
	 *                                  the details
	 * @return The NotificationsPermission details matching the
	 *         notificationsPermissionId. Throws {@link RecordNotFoundException} if
	 *         no match is found
	 */
	public NotificationsPermissionVO getNotificationsPermissionById(Long notificationsPermissionId);

}
