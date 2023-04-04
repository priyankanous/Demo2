package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.ProbabilityTypeVO;

public interface ProbabilityTypeService {
	
	/**
	 * Get all the ProbabilityType
	 * 
	 * @return List of all ProbabilityType in the database
	 */

	public List<ProbabilityTypeVO> getAllProbabilityType();
	
	
	/**
	 * Add an ProbabilityType to the database
	 * 
	 * @param ProbabilityTypeVO
	 * @return The newly added ProbabilityType details
	 */
	
	public ProbabilityTypeVO saveProbabilityType(ProbabilityTypeVO probabilityTypeVO);
	

	/**
	 * Delete an ProbabilityType record by given Id
	 * 
	 * @param probabilityTypeId The probabilityTypeId of the ProbabilityType to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteProbabilityTypeById(Long probabilityTypeId);
	
	
	/**
	 * Get the ProbabilityType details by given Id 
	 * 
	 * @param  probabilityTypeId The probabilityTypeId for retrieving the details
	 * @return The ProbabilityType details matching the ProbabilityType id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public ProbabilityTypeVO getProbabilityTypeById(Long probabilityTypeId);
	
	
	/**
	 * Update an ProbabilityType to the database by given Id
	 * 
	 * @param probabilityTypeId, ProbabilityTypeVO
	 * @return The newly updated ProbabilityType details
	 */

	public  ProbabilityTypeVO updateProbabilityType(Long probabilityTypeId, ProbabilityTypeVO probabilityTypeVO);


	public List<ProbabilityTypeVO> getPagination(int pagenumber, int pagesize, String sortBy);

}
