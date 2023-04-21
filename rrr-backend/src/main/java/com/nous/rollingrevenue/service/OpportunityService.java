package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.OpportunityVO;

public interface OpportunityService {
	
	/**
	 * Get all the Opportunity
	 * 
	 * @return List of all Opportunity in the database
	 */

	public List<OpportunityVO> getAllOpportunity();
	
	
	/**
	 * Add an Opportunity to the database
	 * 
	 * @param OpportunityVO
	 * @return The newly added Opportunity details
	 */
	
	public OpportunityVO saveOpportunity(OpportunityVO opportunityVO);
	

	/**
	 * Delete an Opportunity record by given Id
	 * 
	 * @param opportunityId The opportunityId of the Opportunity to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteOpportunityById(Long opportunityId);
	
	
	/**
	 * Get the Opportunity details by given Id 
	 * 
	 * @param  opportunityId The opportunityId for retrieving the details
	 * @return The Opportunity details matching the Opportunity id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public OpportunityVO getOpportunityById(Long opportunityId);
	
	
	/**
	 * Update an Opportunity to the database by given Id
	 * 
	 * @param opportunityId, OpportunityVO
	 * @return The newly updated Opportunity details
	 */

	public  OpportunityVO updateOpportunity(Long opportunityId, OpportunityVO opportunityVO);


	public List<OpportunityVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public OpportunityVO activateOrDeactivateById(Long id);

	
	/**
	 * Get the Opportunity details by given AccountId 
	 * 
	 * @param  accountId The accountId for retrieving the details of opportunities
	 * @return The Opportunity details matching the account id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	List<OpportunityVO> getOpportunityByAccountId(Long accountId);


}
