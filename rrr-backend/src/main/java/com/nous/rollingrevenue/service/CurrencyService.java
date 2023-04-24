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
	 * 
	 */
	
	public void saveCurrency(CurrencyVO currencyVO);
	

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
	 *
	 */

	public  void updateCurrency(Long currencyId, CurrencyVO currencyVO);


	public List<CurrencyVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public void activateOrDeactivateById(Long id);
	
	/**
	 * Get the Currency details
	 * 
	 * @param financialYear
	 * @return Currency. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public List<CurrencyVO> getCurrencyByFinancialYear(String financialYear);
	
	

	/**
	 * Get the list of Currencies details
	 * 
	 * @param List<Currencies>
	 * @return 
	 *         
	 */
	public void saveListOfCurrency(List<CurrencyVO> currencyVO);

}
