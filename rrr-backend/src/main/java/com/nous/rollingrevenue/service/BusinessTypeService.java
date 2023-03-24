package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.BusinessTypeVO;

public interface BusinessTypeService {
	
	/**
	 * Get all the BusinessType
	 * 
	 * @return List of all BusinessType in the database
	 */

	public List<BusinessTypeVO> getAllBusinessType();
	
	
	/**
	 * Add an business type to the database
	 * 
	 * @param BusinessTypeVO
	 * @return The newly added BusinessType details
	 */
	
	public BusinessTypeVO saveBusinessType(BusinessTypeVO businessTypeVO);
	

	/**
	 * Delete an BusinessType record by given Id
	 * 
	 * @param BusinessTypeId The businessTypeId of the BusinessType to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteBusinessTypeById(Long businessTypeId);
	
	
	/**
	 * Get the BusinessType details by given Id 
	 * 
	 * @param  businessTypeId The businessTypeId for retrieving the details
	 * @return The BusinessType details matching the BusinessType id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public BusinessTypeVO getBusinessTypeById(Long businessTypeId);
	
	
	/**
	 * Update an BusinessType to the database by given Id
	 * 
	 * @param businessTypeId, BusinessTypeVO
	 * @return The newly updated BusinessType details
	 */

	public  BusinessTypeVO updateBusinessType(Long businessTypeId, BusinessTypeVO businessTypeVO);

}
