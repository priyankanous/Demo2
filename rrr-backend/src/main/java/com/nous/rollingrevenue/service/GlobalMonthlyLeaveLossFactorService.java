package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.GlobalMonthlyLeaveLossFactorVO;

public interface GlobalMonthlyLeaveLossFactorService {

	/**
	 * Add an Global monthly leave loss factor to the database
	 * 
	 * @param leaveLossFactorVO
	 * @return The newly added Global monthly leave loss factor details
	 */
	public GlobalMonthlyLeaveLossFactorVO addLeaveLossFactor(GlobalMonthlyLeaveLossFactorVO leaveLossFactorVO);

	/**
	 * Update an Global monthly leave loss factor to the database
	 * 
	 * @param leaveLossFactorVO
	 * @return updated Global monthly leave loss factor details
	 */
	public GlobalMonthlyLeaveLossFactorVO updateLeaveLossFactor(Long id, GlobalMonthlyLeaveLossFactorVO factorVO);

	/**
	 * Get the Global monthly leave loss factor details
	 * 
	 * @param id
	 * @return Global monthly leave loss factor. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public GlobalMonthlyLeaveLossFactorVO getLeaveLossFactorById(Long id);

	/**
	 * Delete an Global monthly leave loss factor record
	 * 
	 * @param id The id of the Global monthly leave loss factor to be deleted
	 */
	public void deleteLeaveLossFactor(Long id);

	/**
	 * Get all the Global monthly leave loss factors
	 * 
	 * @return List of all Global monthly leave loss factor in the database
	 */
	public List<GlobalMonthlyLeaveLossFactorVO> getLeaveLossFactors();

}
