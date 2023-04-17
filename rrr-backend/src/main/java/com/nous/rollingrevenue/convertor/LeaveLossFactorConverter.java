package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.GlobalMonthlyLeaveLossFactor;
import com.nous.rollingrevenue.vo.GlobalMonthlyLeaveLossFactorVO;

@Component
public class LeaveLossFactorConverter {

	/**
	 * Convert GlobalMonthlyLeaveLossFactorVO to GlobalMonthlyLeaveLossFactor
	 * 
	 * @param GlobalMonthlyLeaveLossFactorVO
	 * @return GlobalMonthlyLeaveLossFactor Jpa bean
	 */
	public static GlobalMonthlyLeaveLossFactor convertLeaveLossFactorVOToLeaveLossFactor(
			GlobalMonthlyLeaveLossFactorVO leaveLossFactorVO) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = new GlobalMonthlyLeaveLossFactor();
		if (leaveLossFactorVO != null) {
			leaveLossFactor.setLeaveLossFactorId(leaveLossFactorVO.getLeaveLossFactorId());
			leaveLossFactor.setMonth(leaveLossFactorVO.getMonth());
			leaveLossFactor.setOffShore(leaveLossFactorVO.getOffShore());
			leaveLossFactor.setOnSite(leaveLossFactorVO.getOnSite());
			leaveLossFactor.setFinancialYear(FinancialYearConverter
					.convertFinancialYearVOToFinancialYear(leaveLossFactorVO.getFinancialYear()));
		}
		return leaveLossFactor;
	}

	/**
	 * Convert GlobalMonthlyLeaveLossFactor to GlobalMonthlyLeaveLossFactorVO
	 * 
	 * @param GlobalMonthlyLeaveLossFactor
	 * @return GlobalMonthlyLeaveLossFactorVO
	 */
	public static GlobalMonthlyLeaveLossFactorVO convertLeaveLossFactorToLeaveLossFactorVO(
			GlobalMonthlyLeaveLossFactor leaveLossFactor) {
		GlobalMonthlyLeaveLossFactorVO leaveLossFactorVO = new GlobalMonthlyLeaveLossFactorVO();
		if (leaveLossFactor != null) {
			leaveLossFactorVO.setLeaveLossFactorId(leaveLossFactor.getLeaveLossFactorId());
			leaveLossFactorVO.setMonth(leaveLossFactor.getMonth());
			leaveLossFactorVO.setOffShore(leaveLossFactor.getOffShore());
			leaveLossFactorVO.setOnSite(leaveLossFactor.getOnSite());
			leaveLossFactorVO.setFinancialYear(
					FinancialYearConverter.convertFinancialYearToFinancialYearVO(leaveLossFactor.getFinancialYear()));
			leaveLossFactorVO.setActive(leaveLossFactor.isActive());
		}
		return leaveLossFactorVO;
	}

}
