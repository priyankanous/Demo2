package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.WorkOrder;
import com.nous.rollingrevenue.vo.WorkOrderVO;

@Component
public class WorkOrderConverter {

	private WorkOrderConverter() {
		super();
	}

	/**
	 * Convert WorkOrderVO to WorkOrder
	 * 
	 * @param WorkOrderVO
	 * @return WorkOrder
	 */

	public static WorkOrder convertWorkOrderVOToWorkOrder(WorkOrderVO workOrderVO) {
		WorkOrder workOrder = new WorkOrder();
		if (workOrderVO != null) {
			workOrder.setWorkOrderId(workOrderVO.getWorkOrderId());
			workOrder.setWorkOrderNumber(workOrderVO.getWorkOrderNumber());
			workOrder.setWorkOrderEndDate(workOrderVO.getWorkOrderEndDate());
			workOrder.setAccount(AccountConverter.convertAccountVOToAccount(workOrderVO.getAccount()));
			workOrder.setWoStatus(workOrderVO.getWorkOrderStatus());

		}
		return workOrder;
	}

	/**
	 * Convert WorkOrder to WorkOrderVO
	 * 
	 * @param WorkOrder
	 * @return WorkOrderVO
	 */

	public static WorkOrderVO convertWorkOrderToWorkOrderVO(WorkOrder workOrder) {
		WorkOrderVO workOrderVO = new WorkOrderVO();
		if (workOrder != null) {
			workOrderVO.setWorkOrderId(workOrder.getWorkOrderId());
			workOrderVO.setWorkOrderNumber(workOrder.getWorkOrderNumber());
			workOrderVO.setWorkOrderEndDate(workOrder.getWorkOrderEndDate());
			workOrderVO.setAccount(AccountConverter.convertAccountToAccountVO(workOrder.getAccount()));
			workOrderVO.setWorkOrderStatus(workOrder.getWoStatus());
			workOrderVO.setActive(workOrder.isActive());

		}
		return workOrderVO;
	}

}
