package com.nous.rollingrevenue.service;

	import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.vo.CocPracticeVO;

	public interface CocPracticeService {
		
		/**
		 * Get all the SBU
		 * 
		 * @return List of all SBU in the database
		 */
		
		public List<CocPractice> getAllCocPractice();
		
		/**
		 * Add an Coc to the database
		 * 
		 * @param CocPracticeServiceVO
		 * @return The newly added CocPractice details
		 */
		
		public CocPractice addCocPractice(CocPractice cocpractice);
		
		/**
		 * Delete an coc record by given Id
		 * 
		 * @param cocId The CocId of the Coc to be deleted. Throws
		 *         {@link RecordNotFoundException} if no match is found
		 */

		public void deleteCocPractice(Long id);
		
		/**
		 * Get the Coc details by given Id 
		 * 
		 * @param  cocId The Coc for retrieving the details
		 * @return The Coc details matching the Coc id. Throws
		 *         {@link RecordNotFoundException} if no match is found
		 */

		public CocPractice getCocPractice(Long id);
		
		/**
		 * Update an Coc to the database by given Id
		 * 
		 * @param cocId, CocPracticeServiceVO
		 * @return The newly updated Coc details
		 */

		public  CocPractice updateCocPractice(Long id, CocPracticeVO cocpracticeVO);

	}


