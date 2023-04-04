package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.CurrencyVO;

public interface CurrencyService {
	
	/**
	 * Get all the Currency
	 * 
	 * @return List of all Currency in the database
	 */

	public List<CurrencyVO> getAllCurrency();
	
	
	/**
	 * Add an Currency to the database
	 * 
	 * @param CurrencyVO
	 * @return The newly added Currency details
	 */
	
	public CurrencyVO saveCurrency(CurrencyVO currencyVO);
	

	/**
	 * Delete an Currency record by given Id
	 * 
	 * @param regionId The currencyId of the Currency to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteCurrencyById(Long currencyId);
	
	
	/**
	 * Get the Currency details by given Id 
	 * 
	 * @param  currencyId The currencyId for retrieving the details
	 * @return The Currency details matching the Currency id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public CurrencyVO getCurrencyById(Long currencyId);
	
	
	/**
	 * Update an Currency to the database by given Id
	 * 
	 * @param currencyId, CurrencyVO
	 * @return The newly updated Currency details
	 */

	public  CurrencyVO updateCurrency(Long currencyId, CurrencyVO currencyVO);


	public List<CurrencyVO> getPagination(int pagenumber, int pagesize, String sortBy);


}
