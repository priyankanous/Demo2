package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.StrategicBusinessUnit;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitVO;

@Component
public class StrategicBusinessUnitConverter {

	/**
	 * Convert StrategicBusinessUnitVO to StrategicBusinessUnit
	 * 
	 * @param StrategicBusinessUnitVO
	 * @return StrategicBusinessUnit
	 */

	public static StrategicBusinessUnit convertSBUVOToSBU(StrategicBusinessUnitVO sbuVO) {
		StrategicBusinessUnit sbu = new StrategicBusinessUnit();
		if (sbuVO != null) {
			sbu.setSbuId(sbuVO.getSbuId());
			sbu.setSbuName(sbuVO.getSbuName());
			sbu.setSbuDisplayName(sbuVO.getSbuDisplayName());
			sbu.setBusinessUnit(BusinessUnitConverter.convertBusinessUnitVOToBusinessUnit(sbuVO.getBusinessUnit()));
		}
		return sbu;
	}

	/**
	 * Convert StrategicBusinessUnit to StrategicBusinessUnitVO
	 * 
	 * @param StrategicBusinessUnit
	 * @return StrategicBusinessUnitVO
	 */

	public static StrategicBusinessUnitVO convertSBUToSBUVO(StrategicBusinessUnit sbu) {
		StrategicBusinessUnitVO sbuVO = new StrategicBusinessUnitVO();
		if (sbu != null) {
			sbuVO.setSbuId(sbu.getSbuId());
			sbuVO.setSbuName(sbu.getSbuName());
			sbuVO.setSbuDisplayName(sbu.getSbuDisplayName());
			sbuVO.setBusinessUnit(BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(sbu.getBusinessUnit()));
			sbuVO.setActive(sbu.isActive());
		}
		return sbuVO;
	}

}
