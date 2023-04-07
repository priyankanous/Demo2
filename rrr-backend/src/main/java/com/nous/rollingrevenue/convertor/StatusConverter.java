package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Status;
import com.nous.rollingrevenue.vo.StatusVO;

@Component
public class StatusConverter {
	
	/**
	 * Convert StatusVO to Status
	 * 
	 * @param StatusVO
	 * @return Status
	 */

	public static Status convertStatusVOToStatus(StatusVO statusVO) {
		Status status = new Status();
		if (statusVO != null) {
			if (statusVO.getStatusId() != null) {
				status.setStatusId(statusVO.getStatusId());
				status.setActive(statusVO.isActive());
			}
			status.setStatusName(statusVO.getStatusName());
			status.setStatusDisplayName(statusVO.getStatusDisplayName());
		}
		return status;
	}
	
	
	/**
	 * Convert Status to StatusVO
	 * 
	 * @param Status
	 * @return StatusVO
	 */

	public static StatusVO convertStatusToStatusVO(Status status) {
		StatusVO statusVO = new StatusVO();
		if (status != null) {
			statusVO.setStatusId(status.getStatusId());
			statusVO.setStatusName(status.getStatusName());
			statusVO.setStatusDisplayName(status.getStatusDisplayName());
			statusVO.setActive(status.isActive());
		}
		return statusVO;
	}


}
