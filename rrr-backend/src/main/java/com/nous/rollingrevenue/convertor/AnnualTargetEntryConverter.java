package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.vo.AnnualTargetEntryVO;

@Component
public class AnnualTargetEntryConverter {

	/**
	 * Convert AnnualTargetEntryVO to AnnualTargetEntry
	 * 
	 * @param AnnualTargetEntryVO
	 * @return AnnualTargetEntry
	 */

	public static AnnualTargetEntry convertAnnualTargetEntryVOToAnnualTargetEntry(AnnualTargetEntryVO annualTargetEntryVO) {
		AnnualTargetEntry annualTargetEntry = new AnnualTargetEntry();
		if (annualTargetEntryVO != null) {
			if (annualTargetEntryVO.getAnnualTargetEntryId() != null) {
				annualTargetEntry.setAnnualTargetEntryId(annualTargetEntryVO.getAnnualTargetEntryId());
				annualTargetEntry.setActive(annualTargetEntryVO.isActive());
			}
			annualTargetEntry.setFinancialYear(annualTargetEntryVO.getFinancialYear());
			annualTargetEntry.setBusinessUnit(annualTargetEntryVO.getBusinessUnit());
			annualTargetEntry.setStartegicBusinessUnit(annualTargetEntryVO.getStartegicBusinessUnit());
			annualTargetEntry.setStrategicBusinessUnitHead(annualTargetEntryVO.getStrategicBusinessUnitHead());
			annualTargetEntry.setLocation(annualTargetEntryVO.getLocation());
			annualTargetEntry.setRegion(annualTargetEntryVO.getRegion());
			annualTargetEntry.setAccount(annualTargetEntryVO.getAccount());
			annualTargetEntry.setBusinessType(annualTargetEntryVO.getBusinessType());
			annualTargetEntry.setCocPractice(annualTargetEntryVO.getCocPractice());
			annualTargetEntry.setBusinessDevelopmentManager(annualTargetEntryVO.getBusinessDevelopmentManager());
			annualTargetEntry.setQ1FYB(annualTargetEntryVO.getQ1FYB());
			annualTargetEntry.setQ1FYS(annualTargetEntryVO.getQ1FYS());
			annualTargetEntry.setQ1FYT(annualTargetEntryVO.getQ1FYT());
			annualTargetEntry.setQ2FYB(annualTargetEntryVO.getQ2FYB());
			annualTargetEntry.setQ2FYS(annualTargetEntryVO.getQ2FYS());
			annualTargetEntry.setQ2FYT(annualTargetEntryVO.getQ2FYT());
			annualTargetEntry.setQ3FYB(annualTargetEntryVO.getQ3FYB());
			annualTargetEntry.setQ3FYS(annualTargetEntryVO.getQ3FYS());
			annualTargetEntry.setQ3FYT(annualTargetEntryVO.getQ3FYT());
			annualTargetEntry.setQ4FYB(annualTargetEntryVO.getQ4FYB());
			annualTargetEntry.setQ4FYS(annualTargetEntryVO.getQ4FYS());
			annualTargetEntry.setQ4FYT(annualTargetEntryVO.getQ4FYT());
			annualTargetEntry.setFY(annualTargetEntryVO.getFY());
			
		}
		return annualTargetEntry;
	}
	
	
	/**
	 * Convert AnnualTargetEntry to AnnualTargetEntryVO
	 * 
	 * @param AnnualTargetEntry
	 * @return AnnualTargetEntryVO
	 */

	public static AnnualTargetEntryVO convertAnnualTargetEntryToAnnualTargetEntryVO(AnnualTargetEntry annualTargetEntry) {
		AnnualTargetEntryVO annualTargetEntryVO = new AnnualTargetEntryVO();
		if (annualTargetEntry != null) {
			annualTargetEntryVO.setAnnualTargetEntryId(annualTargetEntry.getAnnualTargetEntryId());
			annualTargetEntryVO.setFinancialYear(annualTargetEntry.getFinancialYear());
			annualTargetEntryVO.setBusinessUnit(annualTargetEntry.getBusinessUnit());
			annualTargetEntryVO.setStartegicBusinessUnit(annualTargetEntry.getStartegicBusinessUnit());
			annualTargetEntryVO.setStrategicBusinessUnitHead(annualTargetEntry.getStrategicBusinessUnitHead());
			annualTargetEntryVO.setLocation(annualTargetEntry.getLocation());
			annualTargetEntryVO.setRegion(annualTargetEntry.getRegion());
			annualTargetEntryVO.setAccount(annualTargetEntry.getAccount());
			annualTargetEntryVO.setBusinessType(annualTargetEntry.getBusinessType());
			annualTargetEntryVO.setCocPractice(annualTargetEntry.getCocPractice());
			annualTargetEntryVO.setBusinessDevelopmentManager(annualTargetEntry.getBusinessDevelopmentManager());
			annualTargetEntryVO.setQ1FYB(annualTargetEntry.getQ1FYB());
			annualTargetEntryVO.setQ1FYS(annualTargetEntry.getQ1FYS());
			annualTargetEntryVO.setQ1FYT(annualTargetEntry.getQ1FYT());
			annualTargetEntryVO.setQ2FYB(annualTargetEntry.getQ2FYB());
			annualTargetEntryVO.setQ2FYS(annualTargetEntry.getQ2FYS());
			annualTargetEntryVO.setQ2FYT(annualTargetEntry.getQ2FYT());
			annualTargetEntryVO.setQ3FYB(annualTargetEntry.getQ3FYB());
			annualTargetEntryVO.setQ3FYS(annualTargetEntry.getQ3FYS());
			annualTargetEntryVO.setQ3FYT(annualTargetEntry.getQ3FYT());
			annualTargetEntryVO.setQ4FYB(annualTargetEntry.getQ4FYB());
			annualTargetEntryVO.setQ4FYS(annualTargetEntry.getQ4FYS());
			annualTargetEntryVO.setQ4FYT(annualTargetEntry.getQ4FYT());
			annualTargetEntryVO.setFY(annualTargetEntry.getFY());
			annualTargetEntryVO.setActive(annualTargetEntry.isActive());
			
		}
		return annualTargetEntryVO;
	}


}
