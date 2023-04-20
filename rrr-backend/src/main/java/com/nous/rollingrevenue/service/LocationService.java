package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.LocationVO;

public interface LocationService {

	/**
	 * Add an location to the database
	 * 
	 * @param LocationVO
	 * 
	 */
	public void addLocation(LocationVO locationVO);

	/**
	 * Update an location to the database
	 * 
	 * @param id, locationVO
	 * 
	 */
	public void updateLocation(Long id, LocationVO locationVO);

	/**
	 * Get the location details
	 * 
	 * @param id The location id for retrieving the details
	 * @return The Location details matching the Location id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public LocationVO getLocation(Long locationId);

	/**
	 * Delete an Location record
	 * 
	 * @param id The id of the Location to be deleted
	 */
	public void deleteLocation(Long locationId);

	/**
	 * Get all the Locations
	 * 
	 * @return List of all Locations in the database
	 */
	public List<LocationVO> getLocations();

	public List<LocationVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public void activateOrDeactivateById(Long id);

}
