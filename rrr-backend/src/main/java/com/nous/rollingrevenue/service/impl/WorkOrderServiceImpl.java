package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.WorkOrderConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.helper.ExcelHelper;
import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.model.WorkOrder;
import com.nous.rollingrevenue.repository.AccountRepository;
import com.nous.rollingrevenue.repository.WorkOrderRepository;
import com.nous.rollingrevenue.service.WorkOrderService;
import com.nous.rollingrevenue.vo.WorkOrderVO;

@Service
@Transactional(readOnly = true)
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderRepository workOrderRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ExcelHelper excelHelper;

	@Override
	@Transactional
	public void saveExcelDataOfWorkOrder(MultipartFile file) {
		List<WorkOrderVO> excelWorkOrderVOs = excelHelper.convertExceltoListOfWorkOrder(file);
		List<WorkOrder> dbworkOrders = workOrderRepository.findAll();
		if (!dbworkOrders.isEmpty()) {
			Map<Boolean, List<WorkOrderVO>> partitionWorkOrdersByWorkOrderNumber = this
					.getPartitionWorkOrdersByWorkOrderNumber(excelWorkOrderVOs, dbworkOrders);
			Set<Entry<Boolean, List<WorkOrderVO>>> entrySet = partitionWorkOrdersByWorkOrderNumber.entrySet();
			for (Entry<Boolean, List<WorkOrderVO>> entry : entrySet) {
				if (Boolean.TRUE.equals(entry.getKey())) {
					List<WorkOrderVO> workOrderNumberMatch = entry.getValue();
					if (!workOrderNumberMatch.isEmpty()) {
						List<WorkOrderVO> unmatchedWorkOrders = this
								.getUnmatchedWorkOrdersByCheckWithDBWorkOrders(excelWorkOrderVOs, dbworkOrders);
						if (!unmatchedWorkOrders.isEmpty()) {
							this.updateWorkOrder(unmatchedWorkOrders);
						}
					}
				} else {
					List<WorkOrderVO> newWorkOrderVOs = entry.getValue();
					this.saveWorkOrder(newWorkOrderVOs);
				}
			}
		} else {
			this.saveWorkOrder(excelWorkOrderVOs);
		}

	}

	private void saveWorkOrder(List<WorkOrderVO> workOrderVOs) {
		List<WorkOrder> workOrderEntries = new ArrayList<>();
		workOrderVOs.stream().forEach(
				workOrderVO -> workOrderEntries.add(WorkOrderConverter.convertWorkOrderVOToWorkOrder(workOrderVO)));
		workOrderRepository.saveAll(workOrderEntries);
	}

	private void updateWorkOrder(List<WorkOrderVO> workOrderVOs) {
		List<WorkOrder> workOrderEntries = new ArrayList<>();
		workOrderVOs.stream().forEach(
				workOrderVO -> workOrderEntries.add(WorkOrderConverter.convertWorkOrderVOToWorkOrder(workOrderVO)));
		for (WorkOrder workOrder : workOrderEntries) {
			Optional<WorkOrder> wo = workOrderRepository.findByWorkOrderNumber(workOrder.getWorkOrderNumber());
			if (wo.isPresent()) {
				WorkOrder workOrderEntity = wo.get();
				workOrderEntity.setWorkOrderEndDate(workOrder.getWorkOrderEndDate());
				workOrderEntity.setWoStatus(workOrder.getWoStatus());
				workOrderRepository.save(workOrderEntity);
			}
		}
	}

	private List<WorkOrderVO> getUnmatchedWorkOrdersByCheckWithDBWorkOrders(List<WorkOrderVO> workOrderVOs,
			List<WorkOrder> workOrders) {
		return workOrderVOs.stream()
				.filter(wo1 -> workOrders.stream()
						.noneMatch(wo2 -> wo1.getWorkOrderNumber().equals(wo2.getWorkOrderNumber())
								&& wo1.getWorkOrderEndDate().equals(wo2.getWorkOrderEndDate())
								&& wo1.getWorkOrderStatus().equals(wo2.getWoStatus())))
				.toList();
	}

	private Map<Boolean, List<WorkOrderVO>> getPartitionWorkOrdersByWorkOrderNumber(List<WorkOrderVO> excelWorkOrderVOs,
			List<WorkOrder> dbworkOrders) {
		List<String> dbWorkOrderNumbers = dbworkOrders.stream().map(WorkOrder::getWorkOrderNumber).toList();
		return excelWorkOrderVOs.stream()
				.collect(Collectors.partitioningBy(wo -> dbWorkOrderNumbers.contains(wo.getWorkOrderNumber())));
	}

	@Override
	@Transactional
	public void activateOrDeactivateWorkOrderById(Long id) {
		WorkOrder workOrder = workOrderRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		workOrder.setActive(!workOrder.isActive());
		workOrderRepository.save(workOrder);
	}

	@Override
	public List<WorkOrderVO> getWorkOrderByAccountName(String accountName) {
		List<WorkOrderVO> workOrderVOEntries = new ArrayList<>();
		Account findByAccountName = accountRepository.findByAccountName(accountName)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + accountName));
		findByAccountName.getWorkOrders().stream().forEach(
				workOrder -> workOrderVOEntries.add(WorkOrderConverter.convertWorkOrderToWorkOrderVO(workOrder)));
		return workOrderVOEntries;
	}

	@Override
	public List<WorkOrderVO> getAllWorkOrders() {
		List<WorkOrderVO> workOrderVOs = new ArrayList<>();
		workOrderRepository.findAll().stream()
				.forEach(workOrder -> workOrderVOs.add(WorkOrderConverter.convertWorkOrderToWorkOrderVO(workOrder)));
		return workOrderVOs;
	}

	@Override
	public List<WorkOrderVO> getWorkOrdersByPagination(int pagenumber, int pagesize, String sortBy) {
		List<WorkOrderVO> workOrderVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<WorkOrder> pageResult = workOrderRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				workOrderVOs.add(WorkOrderConverter.convertWorkOrderToWorkOrderVO(e));
			});
			return workOrderVOs;
		}
		return Collections.emptyList();
	}

}
