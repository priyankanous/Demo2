package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.CurrencyPermissionVO;

public interface CurrencyPermissionService {

	/**
	 * Get all the CurrencyPermission
	 * 
	 * @return List of all CurrencyPermission in the database
	 */
	public List<CurrencyPermissionVO> getAllCurrencyPermission();

	/**
	 * Add an CurrencyPermission to the database
	 * 
	 * @param CurrencyPermissionVo
	 * 
	 */
	public void saveCurrencyPermission(CurrencyPermissionVO currencyPermissionVO);

	/**
	 * Delete an CurrencyPermission record by given Id
	 * 
	 * @param CurrencyPermission The CurrencyPermissionId of the CurrencyPermission
	 *                           to be deleted. Throws
	 *                           {@link RecordNotFoundException} if no match is
	 *                           found
	 */
	public void deleteCurrencyPermissionById(Long currencyPermissionId);

	/**
	 * Get the CurrencyPermission details by given Id
	 * 
	 * @param CurrencyPermissionId The CurrencyPermissionId for retrieving the
	 *                             details
	 * @return The CurrencyPermission details matching the CurrencyPermission id.
	 *         Throws {@link RecordNotFoundException} if no match is found
	 */
	CurrencyPermissionVO getCurrencyPermissionById(Long currencyPermissionId);

	/**
	 * Update an CurrencyPermission to the database by given Id
	 * 
	 * @param CurrencyPermissionId, CurrencyPermissionVO
	 * 
	 */
	public void updateCurrencyPermission(Long CurrencyPermissionId, CurrencyPermissionVO currencyPermissionVO);

}
