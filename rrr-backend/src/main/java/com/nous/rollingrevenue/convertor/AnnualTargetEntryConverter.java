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
			annualTargetEntry.setAnnualTargetEntryId(annualTargetEntryVO.getAnnualTargetEntryId());
			annualTargetEntry.setFinancialYear(FinancialYearConverter.convertFinancialYearVOToFinancialYear(annualTargetEntryVO.getFinancialYear()));
			annualTargetEntry.setBusinessUnit(BusinessUnitConverter.convertBusinessUnitVOToBusinessUnit(annualTargetEntryVO.getBusinessUnit()));
			annualTargetEntry.setStartegicBusinessUnit(StrategicBusinessUnitConverter.convertSBUVOToSBU(annualTargetEntryVO.getStartegicBusinessUnit()));
			annualTargetEntry.setStrategicBusinessUnitHead(StrategicBusinessUnitHeadConverter.convertSBUHeadVOToSBUHead(annualTargetEntryVO.getStrategicBusinessUnitHead()));
			annualTargetEntry.setLocation(LocationConverter.convertLocationVOToLocation(annualTargetEntryVO.getLocation()));
			annualTargetEntry.setRegion(RegionConverter.convertRegionVOToRegion(annualTargetEntryVO.getRegion()));
			annualTargetEntry.setAccount(AccountConverter.convertAccountVOToAccount(annualTargetEntryVO.getAccount()));
			annualTargetEntry.setBusinessType(BusinessTypeConverter.convertBusinessTypeVOToBusinessType(annualTargetEntryVO.getBusinessType()));
			annualTargetEntry.setCocPractice(CocPracticeConverter.convertCocPracticeVOToCocPractice(annualTargetEntryVO.getCocPractice()));
			annualTargetEntry.setBusinessDevelopmentManager(BusinessDevelopmentManagerConverter.convertBdmVOToBdm(annualTargetEntryVO.getBusinessDevelopmentManager()));
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
			annualTargetEntryVO.setFinancialYear(FinancialYearConverter.convertFinancialYearToFinancialYearVO(annualTargetEntry.getFinancialYear()));
			annualTargetEntryVO.setBusinessUnit(BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(annualTargetEntry.getBusinessUnit()));
			annualTargetEntryVO.setStartegicBusinessUnit(StrategicBusinessUnitConverter.convertSBUToSBUVO(annualTargetEntry.getStartegicBusinessUnit()));
			annualTargetEntryVO.setStrategicBusinessUnitHead(StrategicBusinessUnitHeadConverter.convertSBUHeadToSBUHeadVO(annualTargetEntry.getStrategicBusinessUnitHead()));
			annualTargetEntryVO.setLocation(LocationConverter.convertLocationToLocationVO(annualTargetEntry.getLocation()));
			annualTargetEntryVO.setRegion(RegionConverter.convertRegionToRegionVO(annualTargetEntry.getRegion()));
			annualTargetEntryVO.setAccount(AccountConverter.convertAccountToAccountVO(annualTargetEntry.getAccount()));
			annualTargetEntryVO.setBusinessType(BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(annualTargetEntry.getBusinessType()));
			annualTargetEntryVO.setCocPractice(CocPracticeConverter.convertCocPracticeToCocPracticeVO(annualTargetEntry.getCocPractice()));
			annualTargetEntryVO.setBusinessDevelopmentManager(BusinessDevelopmentManagerConverter.convertBdmToBdmVO(annualTargetEntry.getBusinessDevelopmentManager()));
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
