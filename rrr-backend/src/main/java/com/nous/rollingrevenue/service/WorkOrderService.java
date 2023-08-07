package com.nous.rollingrevenue.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nous.rollingrevenue.vo.WorkOrderVO;

public interface WorkOrderService {

	/**
	 * Add Excel data WorkOrder to the database
	 * 
	 * @param MultipartFile Contains WorkOrderEntries
	 * 
	 */

	public void saveExcelDataOfWorkOrder(MultipartFile file);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public void activateOrDeactivateWorkOrderById(Long id);

	/**
	 * Get WorkOrder By AccountName
	 * @param accountName
	 * @return  List<WorkOrderVO> matched with the account name
	 */
	public List<WorkOrderVO> getWorkOrderByAccountName(String accountName);
	
	/**
	 * Get all the Work Orders
	 * 
	 * @return List of all  Work Orders in the database
	 */
	public List<WorkOrderVO> getAllWorkOrders();
	
	public List<WorkOrderVO> getWorkOrdersByPagination(int pagenumber, int pagesize, String sortBy);
}
