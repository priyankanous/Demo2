package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.WorkOrderStatus;
import com.nous.rollingrevenue.vo.WorkOrderStatusVO;

@Component
public class WorkOrderStatusConverter {
	
	/**
	 * Convert WorkOrderStatusVO to WorkOrderStatus
	 * 
	 * @param WorkOrderStatusVO
	 * @return WorkOrderStatus
	 */

	public static WorkOrderStatus convertWorkOrderStatusVOToWorkOrderStatus(WorkOrderStatusVO woStatusVO) {
		WorkOrderStatus woStatus = new WorkOrderStatus();
		if (woStatusVO != null) {
			if (woStatusVO.getWoStatusId() != null) {
				woStatus.setWoStatusId(woStatusVO.getWoStatusId());
				woStatus.setActive(woStatusVO.isActive());
			}
			woStatus.setWoStatusName(woStatusVO.getWoStatusName());
			woStatus.setWoStatusDisplayName(woStatusVO.getWoStatusDisplayName());
		}
		return woStatus;
	}
	
	
	/**
	 * Convert WorkOrderStatus to WorkOrderStatusVO
	 * 
	 * @param WorkOrderStatus
	 * @return WorkOrderStatusVO
	 */

	public static WorkOrderStatusVO convertWorkOrderStatusToWorkOrderStatusVO(WorkOrderStatus woStatus) {
		WorkOrderStatusVO woStatusVO = new WorkOrderStatusVO();
		if (woStatus != null) {
			woStatusVO.setWoStatusId(woStatus.getWoStatusId());
			woStatusVO.setWoStatusName(woStatus.getWoStatusName());
			woStatusVO.setWoStatusDisplayName(woStatus.getWoStatusDisplayName());
			woStatusVO.setActive(woStatus.isActive());
		}
		return woStatusVO;
	}


}
