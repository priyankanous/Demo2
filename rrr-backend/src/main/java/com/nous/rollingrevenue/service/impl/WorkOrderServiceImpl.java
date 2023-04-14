package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.WorkOrderConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.helper.ExcelHelper;
import com.nous.rollingrevenue.model.WorkOrder;
import com.nous.rollingrevenue.repository.WorkOrderRepository;
import com.nous.rollingrevenue.service.WorkOrderService;
import com.nous.rollingrevenue.vo.WorkOrderVO;

@Service
@Transactional(readOnly = true)
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderRepository workOrderRepository;

	@Override
	@Transactional
	public void saveExcelDataOfWorkOrder(MultipartFile file) {
		List<WorkOrderVO> workOrderVOs = ExcelHelper.convertExceltoListOfWorkOrder(file);
		List<WorkOrder> workOrderEntries = new ArrayList<>();
		workOrderVOs.stream().forEach(
				workOrderVO -> workOrderEntries.add(WorkOrderConverter.convertWorkOrderVOToWorkOrder(workOrderVO)));
		workOrderRepository.saveAll(workOrderEntries);

	}
	
	@Override
	@Transactional
	public WorkOrderVO activateOrDeactivateByWorkOrderNumber(Long workOrderNumber) {
		WorkOrder workOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);
		if (workOrder == null) {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + workOrderNumber);
		}
		workOrder.setActive(!workOrder.isActive());
		return WorkOrderConverter.convertWorkOrderToWorkOrderVO(workOrderRepository.save(workOrder));
	}

	@Override
	public List<WorkOrderVO> getWorkOrderByAccountName(String accountName) {
		List<WorkOrderVO> workOrderVOEntries = new ArrayList<>();
		workOrderRepository.findByAccountName(accountName).stream().forEach(
				workOrder -> workOrderVOEntries.add(WorkOrderConverter.convertWorkOrderToWorkOrderVO(workOrder)));
		return workOrderVOEntries;
	}

}
