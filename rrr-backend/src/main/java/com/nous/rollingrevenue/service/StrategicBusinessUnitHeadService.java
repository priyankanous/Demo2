package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
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
	 * 
	 */

	public void saveSBUHead(StrategicBusinessUnitHeadVO sbuHeadVO);

	/**
	 * Delete an SBUHead record by given Id
	 * 
	 * @param sbuHeadId The SBUHeadId of the SBUHead to be deleted. Throws
	 *                  {@link RecordNotFoundException} if no match is found
	 */

	public void deleteSBUHeadById(Long sbuHeadId);

	/**
	 * Get the SBUHead details by given Id
	 * 
	 * @param sbuHeadId The SBUHead for retrieving the details
	 * @return The SBUHead details matching the SBUHead id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public StrategicBusinessUnitHeadVO getSBUHeadById(Long sbuHeadId);

	/**
	 * Update an SBUHead to the database by given Id
	 * 
	 * @param sbuHeadId, StrategicBusinessUnitHeadVO
	 *
	 */

	public void updateSBUHead(Long sbuHeadId, StrategicBusinessUnitHeadVO sbuHeadVO);

	public List<StrategicBusinessUnitHeadVO> getPagination(int pagenumber, int pagesize, String sortBy);

	/**
	 * Activate or Deactivate the record
	 * 
	 * @param id
	 */
	public void activateOrDeactivateById(Long id);

	/**
	 * Get the SBU Head details by given SBU Id
	 * 
	 * @param SBU Id The SBU Id for retrieving the details of
	 *            StrategicBusinessUnitHead
	 * @return The StrategicBusinessUnitHead details matching the SBU id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public List<StrategicBusinessUnitHeadVO> getSBUHeadBySBUId(Long sbuId);

}
