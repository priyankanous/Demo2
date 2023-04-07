package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.vo.StrategicBusinessUnitVO;

public interface StrategicBusinessUnitService {
	
	/**
	 * Get all the SBU
	 * 
	 * @return List of all SBU in the database
	 */
	
	public List<StrategicBusinessUnitVO> getAllSBU();
	
	/**
	 * Add an SBU to the database
	 * 
	 * @param StrategicBusinessUnitVO
	 * @return The newly added StrategicBusinessUnit details
	 */
	
	public StrategicBusinessUnitVO saveSBU(StrategicBusinessUnitVO sbuVO);
	
	/**
	 * Delete an SBU record by given Id
	 * 
	 * @param sbuId The SBUId of the SBU to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteSBUById(Long sbuId);
	
	/**
	 * Get the SBU details by given Id 
	 * 
	 * @param  sbuId The SBU for retrieving the details
	 * @return The SBU details matching the SBU id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public StrategicBusinessUnitVO getSBUById(Long sbuId);
	
	/**
	 * Update an SBU to the database by given Id
	 * 
	 * @param sbuId, StrategicBusinessUnitVO
	 * @return The newly updated SBU details
	 */

	public  StrategicBusinessUnitVO updateSBU(Long sbuId, StrategicBusinessUnitVO sbuVO);

	public List<StrategicBusinessUnitVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public StrategicBusinessUnitVO activateOrDeactivateById(Long id);
	
}
