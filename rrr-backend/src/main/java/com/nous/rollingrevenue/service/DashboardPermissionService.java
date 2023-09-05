package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.DashboardPermissionVO;

public interface DashboardPermissionService {

	/**
	 * Get all the DashboardPermission
	 * 
	 * @return List of all DashboardPermission in the database
	 */

	public List<DashboardPermissionVO> getAllDashboardPermission();

	/**
	 * Add an DashboardPermission to the database
	 * 
	 * @param DashboardPermissionVo
	 * 
	 */

	public void saveDashboardPermission(DashboardPermissionVO dashboardPermissionVO);

	/**
	 * Delete an DashboardPermission record by given Id
	 * 
	 * @param DashboardPermission The DashboardPermissionId of the
	 *                            DashboardPermission to be deleted. Throws
	 *                            {@link RecordNotFoundException} if no match is
	 *                            found
	 */

	public void deleteDashboardPermissionById(Long dashboardPermissionId);

	/**
	 * Get the DashboardPermission details by given Id
	 * 
	 * @param DashboardPermissionId The DashboardPermissionId for retrieving the
	 *                              details
	 * @return The DashboardPermission details matching the DashboardPermission id.
	 *         Throws {@link RecordNotFoundException} if no match is found
	 */

	public DashboardPermissionVO getDashboardPermissionById(Long dashboardPermissionId);

	/**
	 * Update an DashboardPermission to the database by given Id
	 * 
	 * @param DashboardPermissionId, DashboardPermissionVO
	 * 
	 */

	public void updateDashboardPermission(Long DashboardPermissionId, DashboardPermissionVO dashboardPermissionVO);

}
