package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.RollingRevenueEntryPermissionVO;

public interface RollingRevenueEntryPermissionService {

	/**
	 * Add an RollingRevenueEntryPermission to the database
	 * 
	 * @param RollingRevenueEntryPermissionVO
	 * 
	 */
	void saveRollingRevenueEntryPermission(RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO);

	/**
	 * Update an RollingRevenueEntryPermission to the database by given
	 * rollingrevenueEntryPermissionId
	 * 
	 * @param RollingrevenueEntryPermissionId, RollingRevenueEntryPermissionVO
	 * 
	 */
	void updateRollingRevenueEntryPermissionById(Long rollingrevenueEntryPermissionId,
			RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO);

	/**
	 * Delete an RollingRevenueEntryPermission record by given
	 * rollingrevenueEntryPermissionId
	 * 
	 * @param rollingrevenueEntryPermissionId The rollingrevenueEntryPermissionId of
	 *                                        the RollingRevenueEntryPermission to
	 *                                        be deleted. Throws
	 *                                        {@link RecordNotFoundException} if no
	 *                                        match is found
	 */
	void deleteRollingRevenueEntryPermissionById(Long rollingrevenueEntryPermissionId);

	/**
	 * Get all the RollingRevenueEntryPermission
	 * 
	 * @return List of all RollingRevenueEntryPermissions in the database
	 */
	List<RollingRevenueEntryPermissionVO> getAllRollingRevenueEntryPermissions();

	/**
	 * Get the RollingRevenueEntryPermission details by given
	 * rollingrevenueEntryPermissionId
	 * 
	 * @param rollingrevenueEntryPermissionId The rollingrevenueEntryPermissionId
	 *                                        for retrieving the details
	 * @return The RollingRevenueEntryPermission details matching the
	 *         rollingrevenueEntryPermission Id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public RollingRevenueEntryPermissionVO getRollingRevenueEntryPermissionById(Long rollingrevenueEntryPermissionId);

}
