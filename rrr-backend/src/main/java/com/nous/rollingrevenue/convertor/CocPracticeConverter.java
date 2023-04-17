package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.vo.CocPracticeVO;

/**
 * @author Nous Infosystems
 *
 */
@Component
public class CocPracticeConverter {
	/**
	 * Convert CocPracticeVO to CocPractice
	 * 
	 * @param CocPracticeVO
	 * @return cocpractice Jpa bean
	 */
	public static CocPractice convertCocPracticeVOToCocPractice(CocPracticeVO cocpracticeVO) {
		CocPractice cocpractice = new CocPractice();
		if (cocpracticeVO != null) {
		cocpractice.setCocPracticeId(cocpracticeVO.getCocPracticeId());
		cocpractice.setCocPracticeId(cocpracticeVO.getCocPracticeId());
		cocpractice.setCocPracticeName(cocpracticeVO.getCocPracticeName());
		cocpractice.setCocPracticeDisplayName(cocpracticeVO.getCocPracticeDisplayName());
		cocpractice.setBusinessUnit(BusinessUnitConverter.convertBusinessUnitVOToBusinessUnit(cocpracticeVO.getBusinessUnit()));
		}
		return cocpractice;
	}

	/**
	 * Convert CocPractice to CocPracticeVO
	 * 
	 * @param CocPractice cocpractice
	 * @return CocPracticeVO
	 */
	public static CocPracticeVO convertCocPracticeToCocPracticeVO(CocPractice cocpractice) {
		CocPracticeVO cocpracticeVO = new CocPracticeVO();
		if (cocpractice != null) {
			cocpracticeVO.setCocPracticeId(cocpractice.getCocPracticeId());
			cocpracticeVO.setCocPracticeName(cocpractice.getCocPracticeName());
			cocpracticeVO.setCocPracticeDisplayName(cocpractice.getCocPracticeDisplayName());
			cocpracticeVO.setBusinessUnit(BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(cocpractice.getBusinessUnit()));
			cocpracticeVO.setActive(cocpractice.isActive());
		}
		return cocpracticeVO;
	}

}
