package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.ReportsCommonPermissionVO;

public interface ReportsCommonPermissionService {

	/**
	 * Add an ReportsCommonPermission to the database
	 * 
	 * @param ReportsCommonPermissionVO
	 * 
	 */
	void saveReportsCommonPermission(ReportsCommonPermissionVO reportsCommonPermissionVO);

	/**
	 * Update an ReportsCommonPermission to the database by given
	 * reportsCommonPermissionId
	 * 
	 * @param ReportsCommonPermissionId, ReportsCommonPermissionVO
	 * 
	 */
	void updateReportsCommonPermissionById(Long reportsCommonPermissionId,
			ReportsCommonPermissionVO reportsCommonPermissionVO);

	/**
	 * Delete an ReportsCommonPermission record by given reportsCommonPermissionId
	 * 
	 * @param reportsCommonPermissionId The reportsCommonPermissionId of the
	 *                                  ReportsCommonPermission to be deleted.
	 *                                  Throws {@link RecordNotFoundException} if no
	 *                                  match is found
	 */
	void deleteReportsCommonPermissionById(Long reportsCommonPermissionId);

	/**
	 * Get all the ReportsCommonPermission
	 * 
	 * @return List of all ReportsCommonPermissions in the database
	 */
	List<ReportsCommonPermissionVO> getAllReportsCommonPermissions();

	/**
	 * Get the ReportsCommonPermission details by given reportsCommonPermissionId
	 * 
	 * @param reportsCommonPermissionId The reportsCommonPermissionId for retrieving
	 *                                  the details
	 * @return The ReportsCommonPermission details matching the
	 *         reportsCommonPermission Id. Throws {@link RecordNotFoundException} if
	 *         no match is found
	 */
	public ReportsCommonPermissionVO getReportsCommonPermissionById(Long reportsCommonPermissionId);

}