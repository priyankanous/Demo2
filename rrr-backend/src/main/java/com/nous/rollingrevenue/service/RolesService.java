package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.RolesVO;

public interface RolesService {

	/**
	 * Add an Role Details to the database
	 * 
	 * @param rolesVO
	 */
	void saveRolesDetails(RolesVO rolesVO);

	/**
	 * Update an Role Details to the database
	 * 
	 * @param roleId, rolesVO
	 */
	void updateRolesDetails(Long roleId, RolesVO rolesVO);

}
