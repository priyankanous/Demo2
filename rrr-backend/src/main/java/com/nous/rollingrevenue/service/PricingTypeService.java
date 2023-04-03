package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.PricingTypeVO;

public interface PricingTypeService {
	
	/**
	 * Get all the PricingType
	 * 
	 * @return List of all PricingType in the database
	 */

	public List<PricingTypeVO> getAllPricingType();
	
	
	/**
	 * Add an PricingType to the database
	 * 
	 * @param PricingTypeVO
	 * @return The newly added PricingType details
	 */
	
	public PricingTypeVO savePricingType(PricingTypeVO pricingTypeVO);
	

	/**
	 * Delete an PricingType record by given Id
	 * 
	 * @param pricingTypeId The pricingTypeId of the PricingType to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deletePricingTypeById(Long pricingTypeId);
	
	
	/**
	 * Get the PricingType details by given Id 
	 * 
	 * @param  pricingTypeId The pricingTypeId for retrieving the details
	 * @return The PricingType details matching the PricingType id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public PricingTypeVO getPricingTypeById(Long pricingTypeId);
	
	
	/**
	 * Update an PricingType to the database by given Id
	 * 
	 * @param pricingTypeId, PricingTypeVO
	 * @return The newly updated PricingType details
	 */

	public  PricingTypeVO updatePricingType(Long pricingTypeId, PricingTypeVO pricingTypeVO);


	public List<PricingTypeVO> getPagination(int pagenumber, int pagesize, String sortBy);


}
