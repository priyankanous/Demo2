package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.WorkOrderStatusVO;

public interface WorkOrderStatusService {
	
	/**
	 * Get all the WorkOrderStatus
	 * 
	 * @return List of all WorkOrderStatus in the database
	 */

	public List<WorkOrderStatusVO> getAllWorkOrderStatus();
	
	
	/**
	 * Add an WorkOrderStatus to the database
	 * 
	 * @param WorkOrderStatusVO
	 * @return The newly added WorkOrderStatus details
	 */
	
	public WorkOrderStatusVO saveWorkOrderStatus(WorkOrderStatusVO woStatusVO);
	

	/**
	 * Delete an WorkOrderStatus record by given Id
	 * 
	 * @param woStatusId The woStatusId of the WorkOrderStatus to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteWorkOrderStatusById(Long woStatusId);
	
	
	/**
	 * Get the WorkOrderStatus details by given Id 
	 * 
	 * @param  woStatusId The woStatusId for retrieving the details
	 * @return The WorkOrderStatus details matching the WorkOrderStatus id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public WorkOrderStatusVO getWorkOrderStatusById(Long woStatusId);
	
	
	/**
	 * Update an WorkOrderStatus to the database by given Id
	 * 
	 * @param woStatusId, WorkOrderStatusVO
	 * @return The newly updated WorkOrderStatus details
	 */

	public  WorkOrderStatusVO updateWorkOrderStatus(Long woStatusId, WorkOrderStatusVO woStatusVO);


	public List<WorkOrderStatusVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public WorkOrderStatusVO activateOrDeactivateById(Long id);

}
