package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.vo.RegionVO;

public interface RegionService {
	
	/**
	 * Get all the Regions
	 * 
	 * @return List of all Regions in the database
	 */

	public List<RegionVO> getAllRegions();
	
	
	/**
	 * Add an Region to the database
	 * 
	 * @param RegionVO
	 * 
	 */
	
	public void saveRegion(RegionVO regionVO);
	

	/**
	 * Delete an Region record by given Id
	 * 
	 * @param regionId The regionId of the Region to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteRegionById(Long regionId);
	
	
	/**
	 * Get the Region details by given Id 
	 * 
	 * @param  regionId The regionId for retrieving the details
	 * @return The Region details matching the Region id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public RegionVO getRegionById(Long regionId);
	
	
	/**
	 * Update an Region to the database by given Id
	 * 
	 * @param RegionId, RegionVO
	 * 
	 */

	public  void updateRegion(Long regionId, RegionVO regionVO);


	public List<RegionVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public void activateOrDeactivateById(Long id);

}
