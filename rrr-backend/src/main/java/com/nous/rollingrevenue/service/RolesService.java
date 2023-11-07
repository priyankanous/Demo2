package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.RolesVO;

public interface RolesService {

	/**
	 * Add an Role Details to the database
	 * 
	 * @param rolesVO
	 */
	void saveRolesDetails(RolesVO rolesVO);

	/**
	 * Delete an Delete record by given Id
	 * 
	 * @param roleId The roleId of the Role to be deleted. Throws
	 *               {@link RecordNotFoundException} if no match is found
	 */
	void deleteRolesById(Long roleId);

	/**
	 * Update an Role Details to the database
	 * 
	 * @param roleId, rolesVO
	 */
	void updateRolesDetails(Long roleId, RolesVO rolesVO);
	
	public List<RolesVO> getPagination(int pagenumber, int pagesize, String sortBy);

	/**
	 * Get the Roles details by given Id
	 * 
	 * @param roleId The roleId for retrieving the details
	 * @return The Role details matching the Role id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public RolesVO getRolesById(Long roleId);

	/**
	 * Get all the Roles
	 * 
	 * @return List of all Roles in the database
	 */
	public List<RolesVO> getAllRoles();

}
