package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.vo.CocPracticeVO;

/**
 * @author Nous Infosystems
 *
 */
@Component
public class CocPracticeVOToCocPractice {
	/**
	 * Convert CocPracticeVO to CocPractice
	 * 
	 * @param CocPracticeVO
	 * @return cocpractice Jpa bean
	 */
	public static CocPractice convertCocPracticeVOToCocPractice(CocPracticeVO cocpracticeVO) {
		CocPractice cocpractice = new CocPractice();
		if (cocpracticeVO != null) {
			if (cocpracticeVO.getCocPracticeId() != null) {
				cocpractice.setCocPracticeId(cocpracticeVO.getCocPracticeId());
			}
			cocpractice.setCocPracticeId(cocpracticeVO.getCocPracticeId());
			cocpractice.setCocPracticeName(cocpracticeVO.getCocPracticeName());
			cocpractice.setCocPracticeDisplayName(cocpracticeVO.getCocPracticeDisplayName());
			cocpractice.setBuDisplayName(cocpracticeVO.getBuDisplayName());
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
			cocpracticeVO.setBuDisplayName(cocpractice.getBuDisplayName());
		}

		return cocpracticeVO;

	}

}
