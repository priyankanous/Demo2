package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.RoleUserAssignmentPermissionVO;

public interface RoleUserAssignmentPermissionService {

	/**
	 * Get all the RoleUserAssignmentPermission
	 * 
	 * @return List of all RoleUserAssignmentPermission in the database
	 */
	public List<RoleUserAssignmentPermissionVO> getAllRoleUserAssignmentPermission();

	/**
	 * Add an RoleUserAssignmentPermission to the database
	 * 
	 * @param RoleUserAssignmentPermissionVo
	 * 
	 */
	public void saveRoleUserAssignmentPermission(RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO);

	/**
	 * Delete an RoleUserAssignmentPermission record by given Id
	 * 
	 * @param CurrencyPermission The RoleUserAssignmentPermissionId of the
	 *                           RoleUserAssignmentPermission to be deleted. Throws
	 *                           {@link RecordNotFoundException} if no match is
	 *                           found
	 */
	public void deleteRoleUserAssignmentPermissionById(Long roleUserAssignmentPermissionId);

	/**
	 * Get the RoleUserAssignmentPermission details by given Id
	 * 
	 * @param RoleUserAssignmentPermissionId The RoleUserAssignmentPermissionId for
	 *                                       retrieving the details
	 * @return The RoleUserAssignmentPermission details matching the
	 *         RoleUserAssignmentPermission id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	RoleUserAssignmentPermissionVO getRoleUserAssignmentPermissionById(Long roleUserAssignmentPermissionId);

	/**
	 * Update an RoleUserAssignmentPermission to the database by given Id
	 * 
	 * @param RoleUserAssignmentPermissionId, RoleUserAssignmentPermissionVO
	 * 
	 */
	public void updateRoleUserAssignmentPermission(Long RoleUserAssignmentPermissionId,
			RoleUserAssignmentPermissionVO roleUserAssignmentPermissionVO);

}
