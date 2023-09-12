package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.RolesPermissionVO;

public interface RolesPermissionService {

	/**
	 * Get all the RolesPermission
	 * 
	 * @return List of all RolesPermission in the database
	 */
	public List<RolesPermissionVO> getAllRolesPermission();

	/**
	 * Add an RolesPermission to the database
	 * 
	 * @param RolesPermissionVO
	 * 
	 */
	public void saveRolesPermission(RolesPermissionVO rolesPermissionVO);

	/**
	 * Delete an RolesPermission record by given Id
	 * 
	 * @param rolesPermissionId The rolesPermissionId of the
	 *                                      RolesPermission to be
	 *                                      deleted. Throws
	 *                                      {@link RecordNotFoundException} if no
	 *                                      match is found
	 */
	public void deleteRolesPermissionById(Long rolesPermissionId);

	/**
	 * Get the RolesPermission details by given Id
	 * 
	 * @param rolesPermissionId The rolesPermissionId for
	 *                                      retrieving the details
	 * @return The RolesPermission details matching the
	 *         RolesPermission id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public RolesPermissionVO getRolesPermissionById(Long rolesPermissionId);

	/**
	 * Update an RolesPermission to the database by given Id
	 * 
	 * @param RolesPermissionId, RolesPermissionVO
	 * 
	 */
	public void updateRolesPermission(Long rolesPermissionId,
			RolesPermissionVO rolesPermissionVO);

}
