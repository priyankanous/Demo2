package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.ProbabilityType;
import com.nous.rollingrevenue.vo.ProbabilityTypeVO;

@Component
public class ProbabilityTypeConverter {
	
	/**
	 * Convert ProbabilityTypeVO to ProbabilityType
	 * 
	 * @param ProbabilityTypeVO
	 * @return ProbabilityType
	 */

	public static ProbabilityType convertProbabilityTypeVOToProbabilityType(ProbabilityTypeVO probabilityTypeVO) {
		ProbabilityType probabilityType = new ProbabilityType();
		if (probabilityTypeVO != null) {
			if (probabilityTypeVO.getProbabilityTypeId() != null) {
				probabilityType.setProbabilityTypeId(probabilityTypeVO.getProbabilityTypeId());
				probabilityType.setActive(probabilityTypeVO.isActive());
			}
			probabilityType.setProbabilityTypeName(probabilityTypeVO.getProbabilityTypeName());
			probabilityType.setPercentage(probabilityTypeVO.getPercentage());
		}
		return probabilityType;
	}
	
	
	/**
	 * Convert ProbabilityType to ProbabilityTypeVO
	 * 
	 * @param ProbabilityType
	 * @return ProbabilityTypeVO
	 */

	public static ProbabilityTypeVO convertProbabilityTypeToProbabilityTypeVO(ProbabilityType probabilityType) {
		ProbabilityTypeVO probabilityTypeVO = new ProbabilityTypeVO();
		if (probabilityType != null) {
			probabilityTypeVO.setProbabilityTypeId(probabilityType.getProbabilityTypeId());
			probabilityTypeVO.setProbabilityTypeName(probabilityType.getProbabilityTypeName());
			probabilityTypeVO.setPercentage(probabilityType.getPercentage());
			probabilityTypeVO.setActive(probabilityType.isActive());
		}
		return probabilityTypeVO;
	}

}
