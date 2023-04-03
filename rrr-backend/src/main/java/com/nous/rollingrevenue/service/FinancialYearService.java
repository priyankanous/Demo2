package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.FinancialYearVO;

public interface FinancialYearService {
	
	/**
	 * Get all the FinancialYear
	 * 
	 * @return List of all FinancialYear in the database
	 */

	public List<FinancialYearVO> getAllFinancialYear();
	
	
	/**
	 * Add an FinancialYear to the database
	 * 
	 * @param FinancialYearVO
	 * @return The newly added FinancialYear details
	 */
	
	public FinancialYearVO saveFinancialYear(FinancialYearVO financialYearVO);
	

	/**
	 * Delete an FinancialYear record by given Id
	 * 
	 * @param financialYearId The financialYearId of the FinancialYear to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteFinancialYearById(Long financialYearId);
	
	
	/**
	 * Get the FinancialYear details by given Id 
	 * 
	 * @param  financialYearId The financialYearId for retrieving the details
	 * @return The FinancialYear details matching the FinancialYear id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public FinancialYearVO getFinancialYearById(Long financialYearId);
	
	
	/**
	 * Update an FinancialYear to the database by given Id
	 * 
	 * @param financialYearId, FinancialYearVO
	 * @return The newly updated FinancialYear details
	 */

	public  FinancialYearVO updateFinancialYear(Long financialYearId, FinancialYearVO financialYearVO);


	public List<FinancialYearVO> getPagination(int pagenumber, int pagesize, String sortBy);


}
