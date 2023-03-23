package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.vo.FinancialYearVO;

@Component
public class FinancialYearConverter {
	
	/**
	 * Convert FinancialYearVO to FinancialYear
	 * 
	 * @param FinancialYearVO
	 * @return FinancialYear
	 */

	public static FinancialYear convertFinancialYearVOToFinancialYear(FinancialYearVO financialYearVO) {
		FinancialYear financialYear = new FinancialYear();
		if (financialYearVO != null) {
			if (financialYearVO.getFinancialYearId() != null) {
				financialYear.setFinancialYearId(financialYearVO.getFinancialYearId());
			}
			financialYear.setFinancialYearName(financialYearVO.getFinancialYearName());
			financialYear.setFinancialYearCustomName(financialYearVO.getFinancialYearCustomName());
			financialYear.setStartingFrom(financialYearVO.getStartingFrom());
			financialYear.setEndingOn(financialYearVO.getEndingOn());
		}
		return financialYear;
	}
	
	
	/**
	 * Convert FinancialYear to FinancialYearVO
	 * 
	 * @param FinancialYear
	 * @return FinancialYearVO
	 */

	public static FinancialYearVO convertFinancialYearToFinancialYearVO(FinancialYear financialYear) {
		FinancialYearVO financialYearVO = new FinancialYearVO();
		if (financialYear != null) {
			financialYearVO.setFinancialYearId(financialYear.getFinancialYearId());
			financialYearVO.setFinancialYearName(financialYear.getFinancialYearName());
			financialYearVO.setFinancialYearCustomName(financialYear.getFinancialYearCustomName());
			financialYearVO.setStartingFrom(financialYear.getStartingFrom());
			financialYearVO.setEndingOn(financialYear.getEndingOn());
		}
		return financialYearVO;
	}


}
