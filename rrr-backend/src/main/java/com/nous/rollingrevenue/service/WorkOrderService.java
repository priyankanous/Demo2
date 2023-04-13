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
	public WorkOrderVO activateOrDeactivateByWorkOrderNumber(Long id);

	/**
	 * Get WorkOrder By AccountName
	 * @param accountName
	 * @return  List<WorkOrderVO> matched with the account name
	 */
	public List<WorkOrderVO> getWorkOrderByAccountName(String accountName);
}
