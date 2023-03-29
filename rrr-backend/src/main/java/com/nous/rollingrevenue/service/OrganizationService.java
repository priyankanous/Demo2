package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.OrganizationVO;

/**
 * 
 * @author Nous Infosystems
 *
 */
public interface OrganizationService {

	/**
	 * Add an organization to the database
	 * 
	 * @param organizationVO The organization information to add
	 * @return The newly added organization details
	 */
	public OrganizationVO addOrganization(OrganizationVO organizationVO);

	/**
	 * Get the organization details
	 * 
	 * @param id The organization id for retrieving the details
	 * @return The organization details matching the organization id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public OrganizationVO getOrganization(Long id);

	/**
	 * 
	 * @param id The id of the organization to be deleted
	 */
	public void deleteOrganization(Long id);

	/**
	 * Get all the organization
	 * 
	 * @return List of all organization in the database
	 */
	public List<OrganizationVO> getAllOrganization();

	/**
	 * Update an Organization to the database by given Id
	 * 
	 * @param organizationId, OrganizationVO
	 * @return The newly updated Organization details
	 */
	public OrganizationVO updateOrganization(Long id, OrganizationVO organizationVO);

}
