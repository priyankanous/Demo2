package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Organization;
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
	 * @param organization The organization information to add
	 * @return The newly added organization details
	 */
	public Organization addOrganization(Organization organization);

	/**
	 * Get the organization details
	 * 
	 * @param id The organization id for retrieving the details
	 * @return The organization details matching the organization id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public Organization getOrganization(Long id);

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
	public List<Organization> getAllOrganization();

	public Organization updateOrganization(Long id,OrganizationVO organizationVO);
	

}
