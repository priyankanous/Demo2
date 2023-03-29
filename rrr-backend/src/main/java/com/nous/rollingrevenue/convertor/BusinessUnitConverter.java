package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.vo.BusinessUnitVO;

@Component
public class BusinessUnitConverter {

	/**
	 * Convert BusinessUnitVO to BusinessUnit
	 * 
	 * @param businessUnitVO
	 * @return BusinessUnit Jpa bean
	 */
	public static BusinessUnit convertBusinessUnitVOToBusinessUnit(BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = new BusinessUnit();
		if (businessUnitVO != null) {
			if (businessUnitVO.getBusinessUnitId() != null) {
				businessUnit.setBusinessUnitId(businessUnitVO.getBusinessUnitId());
			}
			businessUnit.setBusinessUnitName(businessUnitVO.getBusinessUnitName());
			businessUnit.setBusinessUnitDisplayName(businessUnitVO.getBusinessUnitDisplayName());
			businessUnit.setChildOfOrg(businessUnitVO.getChildOfOrg());
		}
		return businessUnit;
	}

	/**
	 * Convert BusinessUnit to BusinessUnitVO
	 * 
	 * @param BusinessUnit businessUnit
	 * @return BusinessUnitVO
	 */
	public static BusinessUnitVO convertBusinessUnitToBusinessUnitVO(BusinessUnit businessUnit) {
		BusinessUnitVO businessUnitVO = new BusinessUnitVO();
		if (businessUnit != null) {
			businessUnitVO.setBusinessUnitId(businessUnit.getBusinessUnitId());
			businessUnitVO.setBusinessUnitName(businessUnit.getBusinessUnitName());
			businessUnitVO.setBusinessUnitDisplayName(businessUnit.getBusinessUnitDisplayName());
			businessUnitVO.setChildOfOrg(businessUnit.getChildOfOrg());
		}

		return businessUnitVO;

	}

}
