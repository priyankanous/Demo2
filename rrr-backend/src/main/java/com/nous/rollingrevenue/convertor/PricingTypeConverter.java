package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.PricingType;
import com.nous.rollingrevenue.vo.PricingTypeVO;

@Component
public class PricingTypeConverter {

	private PricingTypeConverter() {
		super();
	}

	/**
	 * Convert PricingTypeVO to PricingType
	 * 
	 * @param PricingTypeVO
	 * @return PricingType
	 */

	public static PricingType convertPricingTypeVOToPricingType(PricingTypeVO pricingTypeVO) {
		PricingType pricingType = new PricingType();
		if (pricingTypeVO != null) {
			pricingType.setPricingTypeId(pricingTypeVO.getPricingTypeId());
			pricingType.setPricingTypeName(pricingTypeVO.getPricingTypeName());
			pricingType.setPricingTypeDisplayName(pricingTypeVO.getPricingTypeDisplayName());
		}
		return pricingType;
	}

	/**
	 * Convert PricingType to PricingTypeVO
	 * 
	 * @param PricingType
	 * @return PricingTypeVO
	 */

	public static PricingTypeVO convertPricingTypeToPricingTypeVO(PricingType pricingType) {
		PricingTypeVO pricingTypeVO = new PricingTypeVO();
		if (pricingType != null) {
			pricingTypeVO.setPricingTypeId(pricingType.getPricingTypeId());
			pricingTypeVO.setPricingTypeName(pricingType.getPricingTypeName());
			pricingTypeVO.setPricingTypeDisplayName(pricingType.getPricingTypeDisplayName());
			pricingTypeVO.setActive(pricingType.isActive());
		}
		return pricingTypeVO;
	}

}
