package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.StrategicBusinessUnitHead;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitHeadVO;

@Component
public class StrategicBusinessUnitHeadConverter {
	
	/**
	 * Convert StrategicBusinessUnitHeadVO to StrategicBusinessHeadUnit
	 * 
	 * @param StrategicBusinessUnitHeadVO
	 * @return StrategicBusinessUnitHead
	 */
	
	public static StrategicBusinessUnitHead convertSBUHeadVOToSBUHead(StrategicBusinessUnitHeadVO sbuHeadVO) {
		StrategicBusinessUnitHead sbuHead = new StrategicBusinessUnitHead();
		if (sbuHeadVO != null) {
			if (sbuHeadVO.getSbuHeadId() != null) {
				sbuHead.setSbuHeadId(sbuHeadVO.getSbuHeadId());
				sbuHead.setActive(sbuHeadVO.isActive());
			}
			sbuHead.setSbuHeadName(sbuHeadVO.getSbuHeadName());
			sbuHead.setSbuHeadDisplayName(sbuHeadVO.getSbuHeadDisplayName());
			sbuHead.setSbuName(sbuHeadVO.getSbuName());
			sbuHead.setActiveFrom(sbuHeadVO.getActiveFrom());
			sbuHead.setActiveUntil(sbuHeadVO.getActiveUntil());
		}
		return sbuHead;
	}
	
	/**
	 * Convert StrategicBusinessUnitHead to StrategicBusinessHeadUnitVO
	 * 
	 * @param StrategicBusinessUnitHead
	 * @return StrategicBusinessUnitHeadVO
	 */

	public static StrategicBusinessUnitHeadVO convertSBUHeadToSBUHeadVO(StrategicBusinessUnitHead sbuHead) {
		StrategicBusinessUnitHeadVO sbuHeadVO = new StrategicBusinessUnitHeadVO();
		if (sbuHead != null) {
			sbuHeadVO.setSbuHeadId(sbuHead.getSbuHeadId());
			sbuHeadVO.setSbuHeadName(sbuHead.getSbuHeadName());
			sbuHeadVO.setSbuHeadDisplayName(sbuHead.getSbuHeadDisplayName());
			sbuHeadVO.setSbuName(sbuHead.getSbuName());
			sbuHeadVO.setActiveFrom(sbuHead.getActiveFrom());
			sbuHeadVO.setActiveUntil(sbuHead.getActiveUntil());
			sbuHeadVO.setActive(sbuHead.isActive());
		}
		System.out.println(sbuHeadVO);
		return sbuHeadVO;
	}


}
