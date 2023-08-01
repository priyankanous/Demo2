package com.nous.rollingrevenue.convertor;

import com.nous.rollingrevenue.model.BillingType;
import com.nous.rollingrevenue.vo.BillingTypeVO;

public class BillingTypeConverter {
	
	/**
	 * Convert BillingTypeVO to BillingType
	 * 
	 * @param billingTypeVO
	 * @return BillingType Jpa bean
	 */

	public static BillingType convertBillingTypeVOToBillingType(BillingTypeVO billingTypeVO) {
		BillingType billingType = new BillingType();
		if (billingTypeVO != null) {
			billingType.setBillingTypeId(billingTypeVO.getBillingTypeId());
			billingType.setBillingTypeName(billingTypeVO.getBillingTypeName());
			billingType.setBillingTypeDisplayName(billingTypeVO.getBillingTypeDisplayName());
		}
		return billingType;
	}
	
	/**
	 * Convert BillingType to BillingTypeVO
	 * 
	 * @param BillingType billingType
	 * @return BillingTypeVO
	 */

	public static BillingTypeVO convertBillingTypeToBillingTypeVO(BillingType billingType) {
		BillingTypeVO billingTypeVO = new BillingTypeVO();
		if (billingType != null) {
			billingTypeVO.setBillingTypeId(billingType.getBillingTypeId());
			billingTypeVO.setBillingTypeName(billingType.getBillingTypeName());
			billingTypeVO.setBillingTypeDisplayName(billingType.getBillingTypeDisplayName());
			billingTypeVO.setActive(billingType.isActive());
		}
		return billingTypeVO;
	}

}
