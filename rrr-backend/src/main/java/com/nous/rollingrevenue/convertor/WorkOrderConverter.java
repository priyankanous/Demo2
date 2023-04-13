package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;
import com.nous.rollingrevenue.model.WorkOrder;
import com.nous.rollingrevenue.vo.WorkOrderVO;

@Component
public class WorkOrderConverter {

	/**
	 * Convert WorkOrderVO to WorkOrder
	 * 
	 * @param WorkOrderVO
	 * @return WorkOrder
	 */

	public static WorkOrder convertWorkOrderVOToWorkOrder(WorkOrderVO workOrderVO) {
		WorkOrder workOrder = new WorkOrder();
		if (workOrderVO != null) {
			if (workOrderVO.getWorkOrderId() != null) {
				workOrder.setWorkOrderId(workOrderVO.getWorkOrderId());
				workOrder.setActive(workOrderVO.isActive());
			}
			workOrder.setWorkOrderNumber(workOrderVO.getWorkOrderNumber());
			workOrder.setWorkOrderEndDate(workOrderVO.getWorkOrderEndDate());
			workOrder.setAccountName(workOrderVO.getAccountName());
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
			workOrderVO.setAccountName(workOrder.getAccountName());
			workOrderVO.setWorkOrderStatus(workOrder.getWoStatus());
			workOrderVO.setActive(workOrder.isActive());

		}
		return workOrderVO;
	}

}
