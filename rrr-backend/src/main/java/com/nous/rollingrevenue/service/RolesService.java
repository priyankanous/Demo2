package com.nous.rollingrevenue.service;

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
	 *         {@link RecordNotFoundException} if no match is found
	 */
	void deleteRolesById(Long roleId);

	/**
	 * Update an Role Details to the database
	 * 
	 * @param roleId, rolesVO
	 */
	void updateRolesDetails(Long roleId, RolesVO rolesVO);

}
