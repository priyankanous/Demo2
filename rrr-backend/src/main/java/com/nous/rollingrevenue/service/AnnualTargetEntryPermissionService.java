package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.AnnualTargetEntryPermissionVO;

public interface AnnualTargetEntryPermissionService {

	/**
	 * Get all the AnnualTargetEntryPermission
	 * 
	 * @return List of all AnnualTargetEntryPermission in the database
	 */

	public List<AnnualTargetEntryPermissionVO> getAllAnnualTargetEntryPermission();

	/**
	 * Add an AnnualTargetEntryPermission to the database
	 * 
	 * @param AnnualTargetEntryPermissionVO
	 * 
	 */

	public void saveAnnualTargetEntryPermission(AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO);

	/**
	 * Delete an AnnualTargetEntryPermission record by given Id
	 * 
	 * @param annualTargetEntryPermissionId The annualTargetEntryPermissionId of the
	 *                                      AnnualTargetEntryPermission to be
	 *                                      deleted. Throws
	 *                                      {@link RecordNotFoundException} if no
	 *                                      match is found
	 */

	public void deleteAnnualTargetEntryPermissionById(Long annualTargetEntryPermissionId);

	/**
	 * Get the AnnualTargetEntryPermission details by given Id
	 * 
	 * @param annualTargetEntryPermissionId The annualTargetEntryPermissionId for
	 *                                      retrieving the details
	 * @return The AnnualTargetEntryPermission details matching the
	 *         AnnualTargetEntryPermission id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public AnnualTargetEntryPermissionVO getAnnualTargetEntryPermissionById(Long annualTargetEntryPermissionId);

	/**
	 * Update an AnnualTargetEntryPermission to the database by given Id
	 * 
	 * @param AnnualTargetEntryPermissionId, AnnualTargetEntryPermissionVO
	 * 
	 */

	public void updateAnnualTargetEntryPermission(Long annualTargetEntryPermissionId,
			AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO);

}
