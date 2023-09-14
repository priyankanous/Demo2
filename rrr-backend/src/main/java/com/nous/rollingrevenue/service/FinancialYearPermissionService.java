package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.FinancialYearPermissionVO;

public interface FinancialYearPermissionService {

	/**
	 * Add an FinancialYearPermission to the database
	 * 
	 * @param FinancialYearPermissionVO
	 * 
	 */
	void saveFinancialYearPermission(FinancialYearPermissionVO financialYearPermissionVO);

	/**
	 * Update an FinancialYearPermission to the database by given
	 * financialYearPermissionId
	 * 
	 * @param FinancialYearPermissionId, FinancialYearPermissionVO
	 * 
	 */
	void updateFinancialYearPermissionById(Long financialYearPermissionId,
			FinancialYearPermissionVO financialYearPermissionVO);

	/**
	 * Delete an FinancialYearPermission record by given financialYearPermissionId
	 * 
	 * @param financialYearPermissionId The financialYearPermissionId of the
	 *                                  FinancialYearPermission to be deleted.
	 *                                  Throws {@link RecordNotFoundException} if no
	 *                                  match is found
	 */
	void deleteFinancialYearPermissionById(Long financialYearPermissionId);

	/**
	 * Get all the FinancialYearPermission
	 * 
	 * @return List of all FinancialYearPermissions in the database
	 */
	List<FinancialYearPermissionVO> getFinancialYearPermissions();

	/**
	 * Get the FinancialYearPermission details by given financialYearPermissionId
	 * 
	 * @param financialYearPermissionId The financialYearPermissionId for retrieving
	 *                                  the details
	 * @return The FinancialYearPermission details matching the
	 *         financialYearPermissionId. Throws {@link RecordNotFoundException} if
	 *         no match is found
	 */
	public FinancialYearPermissionVO getFinancialYearPermissionById(Long financialYearPermissionId);

}
