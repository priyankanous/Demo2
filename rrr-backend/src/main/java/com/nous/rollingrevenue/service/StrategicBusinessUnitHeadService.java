package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.vo.StrategicBusinessUnitHeadVO;

public interface StrategicBusinessUnitHeadService {
	
	/**
	 * Get all the SBUHead
	 * 
	 * @return List of all SBUHead in the database
	 */
	
	public List<StrategicBusinessUnitHeadVO> getAllSBUHead();
	
	/**
	 * Add an SBUHead to the database
	 * 
	 * @param StrategicBusinessUnitHeadVO
	 * @return The newly added StrategicBusinessUnitHead details
	 */
	
	public StrategicBusinessUnitHeadVO saveSBUHead(StrategicBusinessUnitHeadVO sbuHeadVO);
	
	/**
	 * Delete an SBUHead record by given Id
	 * 
	 * @param sbuHeadId The SBUHeadId of the SBUHead to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteSBUHeadById(Long sbuHeadId);
	
	/**
	 * Get the SBUHead details by given Id 
	 * 
	 * @param  sbuHeadId The SBUHead for retrieving the details
	 * @return The SBUHead details matching the SBUHead id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public StrategicBusinessUnitHeadVO getSBUHeadById(Long sbuHeadId);
	
	/**
	 * Update an SBUHead to the database by given Id
	 * 
	 * @param sbuHeadId, StrategicBusinessUnitHeadVO
	 * @return The newly updated SBUHead details
	 */

	public  StrategicBusinessUnitHeadVO updateSBUHead(Long sbuHeadId, StrategicBusinessUnitHeadVO sbuHeadVO);

	public  List<StrategicBusinessUnitHeadVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public StrategicBusinessUnitHeadVO activateOrDeactivateById(Long id);

}
