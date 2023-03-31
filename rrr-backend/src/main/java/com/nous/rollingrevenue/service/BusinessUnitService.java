package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.vo.BusinessUnitVO;

public interface BusinessUnitService {

	/**
	 * Add an business unit to the database
	 * 
	 * @param businessUnitVO
	 * @return The newly added businessUnit details
	 */
	public BusinessUnitVO addBusinessUnit(BusinessUnitVO businessUnitVO);

	/**
	 * Get the business unit details
	 * 
	 * @param id The business unit id for retrieving the details
	 * @return The BusinessUnit details matching the Business Unit id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public BusinessUnitVO getBusinessUnitById(Long id);

	/**
	 * Delete an BusinessUnit record
	 * 
	 * @param id The id of the BusinessUnit to be deleted
	 */
	public void deleteBusinessUnit(Long id);

	/**
	 * Get all the BusinessUnit
	 * 
	 * @return List of all BusinessUnit in the database
	 */
	public List<BusinessUnitVO> getBusinessUnits();

	/**
	 * Update an business unit to the database
	 * 
	 * @param businessUnitVO
	 * @return updated businessUnit details
	 */
	public BusinessUnitVO updateBusinessUnit(Long id, BusinessUnitVO businessUnitVO);

	public List<BusinessUnitVO> getPagination(int pagenumber, int pagesize, String sortBy);

}
