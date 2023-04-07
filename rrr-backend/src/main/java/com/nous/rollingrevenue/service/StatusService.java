package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.StatusVO;

public interface StatusService {
	
	/**
	 * Get all the Status
	 * 
	 * @return List of all Status in the database
	 */

	public List<StatusVO> getAllStatus();
	
	
	/**
	 * Add an Status to the database
	 * 
	 * @param StatusVO
	 * @return The newly added Status details
	 */
	
	public StatusVO saveStatus(StatusVO statusVO);
	

	/**
	 * Delete an Status record by given Id
	 * 
	 * @param statusId The statusId of the Status to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteStatusById(Long statusId);
	
	
	/**
	 * Get the Status details by given Id 
	 * 
	 * @param  statusId The statusId for retrieving the details
	 * @return The Status details matching the Status id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public StatusVO getStatusById(Long statusId);
	
	
	/**
	 * Update an Status to the database by given Id
	 * 
	 * @param statusId, StatusVO
	 * @return The newly updated Status details
	 */

	public  StatusVO updateStatus(Long statusId, StatusVO statusVO);


	public List<StatusVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public StatusVO activateOrDeactivateById(Long id);

}
