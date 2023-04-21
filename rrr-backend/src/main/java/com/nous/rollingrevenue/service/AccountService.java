package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.AccountVO;

public interface AccountService {
	
	/**
	 * Get all the Account
	 * 
	 * @return List of all Account in the database
	 */

	public List<AccountVO> getAllAccounts();
	
	
	/**
	 * Add an Account to the database
	 * 
	 * @param AccountVO
	 */
	
	public void saveAccount(AccountVO accountVO);
	

	/**
	 * Delete an Account record by given Id
	 * 
	 * @param accountId The accountId of the Account to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteAccountById(Long accountId);
	
	
	/**
	 * Get the Account details by given Id 
	 * 
	 * @param  accountId The accountId for retrieving the details
	 * @return The Account details matching the Account id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public AccountVO getAccountById(Long accountId);
	
	
	/**
	 * Update an Account to the database by given Id
	 * 
	 * @param accountId, AccountVO
	 * 
	 */

	public void updateAccount(Long accountId, AccountVO accountVO);


	public List<AccountVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public void activateOrDeactivateById(Long id);

}
