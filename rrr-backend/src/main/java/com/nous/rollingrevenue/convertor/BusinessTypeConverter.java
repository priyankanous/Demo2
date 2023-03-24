package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.BusinessType;
import com.nous.rollingrevenue.vo.BusinessTypeVO;

@Component
public class BusinessTypeConverter {
	
	/**
	 * Convert BusinessTypeVO to BusinessType
	 * 
	 * @param BusinessTypeVO
	 * @return BusinessType
	 */

	public static BusinessType convertBusinessTypeVOToBusinessType(BusinessTypeVO businessTypeVO) {
		BusinessType businessType = new BusinessType();
		if (businessTypeVO != null) {
			if (businessTypeVO.getBusinessTypeId() != null) {
				businessType.setBusinessTypeId(businessTypeVO.getBusinessTypeId());
			}
			businessType.setBusinessTypeName(businessTypeVO.getBusinessTypeName());
			businessType.setBusinessTypeDisplayName(businessTypeVO.getBusinessTypeDisplayName());
		}
		return businessType;
	}
	
	
	/**
	 * Convert BusinessType to BusinessTypeVO
	 * 
	 * @param BusinessType
	 * @return BusinessTypeVO
	 */

	public static BusinessTypeVO convertBusinessTypeToBusinessTypeVO(BusinessType businessType) {
		BusinessTypeVO businessTypeVO = new BusinessTypeVO();
		if (businessType != null) {
			businessTypeVO.setBusinessTypeId(businessType.getBusinessTypeId());
			businessTypeVO.setBusinessTypeName(businessType.getBusinessTypeName());
			businessTypeVO.setBusinessTypeDisplayName(businessType.getBusinessTypeDisplayName());
		}
		return businessTypeVO;
	}


}
