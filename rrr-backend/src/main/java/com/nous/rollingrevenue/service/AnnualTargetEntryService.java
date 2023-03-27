package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.AnnualTargetEntryVO;

public interface AnnualTargetEntryService {
	
	/**
	 * Get all the AnnualTargetEntry
	 * 
	 * @return List of all AnnualTargetEntry in the database
	 */

	public List<AnnualTargetEntryVO> getAllAnnualTargetEntry();
	
	
	/**
	 * Add an AnnualTargetEntry to the database
	 * 
	 * @param AnnualTargetEntryVO
	 * @return The newly added AnnualTargetEntry details
	 */
	
	public AnnualTargetEntryVO saveAnnualTargetEntry(AnnualTargetEntryVO annualTargetEntryVO);
	

	/**
	 * Delete an AnnualTargetEntry record by given Id
	 * 
	 * @param annualTargetEntryId The annualTargetEntryId of the AnnualTargetEntry to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteAnnualTargetEntryById(Long annualTargetEntryId);
	
	
	/**
	 * Get the AnnualTargetEntry details by given Id 
	 * 
	 * @param  annualTargetEntryId The annualTargetEntryId for retrieving the details
	 * @return The AnnualTargetEntry details matching the AnnualTargetEntry id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public AnnualTargetEntryVO getAnnualTargetEntryById(Long annualTargetEntryId);
	
	
	/**
	 * Update an AnnualTargetEntry to the database by given Id
	 * 
	 * @param AnnualTargetEntryId, AnnualTargetEntryVO
	 * @return The newly updated AnnualTargetEntry details
	 */

	public  AnnualTargetEntryVO updateAnnualTargetEntry(Long annualTargetEntryId, AnnualTargetEntryVO annualTargetEntryVO);

}
