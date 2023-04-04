package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.BDMVO;

public interface BusinessDevelopmentManagerService {

	/**
	 * Add an Business Development Manager to the database
	 * 
	 * @param bdm
	 * @return The newly added Business Development Manager details
	 */
	public BDMVO addBDMDetails(BDMVO bdmvo);

	/**
	 * Update an Business Development Manager to the database
	 * 
	 * @param bdmId, bdmVO
	 * @return updated Business Development Manager details
	 */
	public BDMVO updateBDMDetails(Long bdmId, BDMVO bdmVO);

	/**
	 * Get the Business Development Manager details By Id
	 * 
	 * @param id
	 * @return BusinessDevelopmentManager. Throws {@link RecordNotFoundException} if
	 *         no match is found
	 */
	public BDMVO getBdmById(Long bdmId);

	/**
	 * Delete an Business Development Manager record
	 * 
	 * @param id The id of the Business Development Manager to be deleted
	 */
	public void deleteBDM(Long bdmId);

	/**
	 * Get all the Business Development Manager
	 * 
	 * @return List of all Business Development Manager in the database
	 */
	public List<BDMVO> getBDM();

	public List<BDMVO> getPagination(int pagenumber, int pagesize, String sortBy);

}
