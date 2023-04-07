package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.vo.BDMVO;

@Component
public class BusinessDevelopmentManagerConverter {

	/**
	 * Convert BDM VO to BDM
	 * 
	 * @param BDM Vo
	 * @return BDM Jpa bean
	 */
	public static BusinessDevelopmentManager convertBdmVOToBdm(BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = new BusinessDevelopmentManager();
		if (bdmVO != null) {
			if (bdmVO.getBdmId() != null) {
				bdm.setBdmId(bdmVO.getBdmId());
				bdm.setActive(bdmVO.isActive());
			}
			bdm.setBdmName(bdmVO.getBdmName());
			bdm.setBdmDisplayName(bdmVO.getBdmDisplayName());
			bdm.setActiveFrom(bdmVO.getActiveFrom());
			bdm.setActiveUntil(bdmVO.getActiveUntil());
			bdm.setLinkedToBusinessUnit(bdmVO.getLinkedToBusinessUnit());
			bdm.setLinkedToRegion(bdmVO.getLinkedToRegion());
		}
		return bdm;
	}

	/**
	 * Convert BDM to BDM VO
	 * 
	 * @param BDM bdm
	 * @return BDM VO
	 */
	public static BDMVO convertBdmToBdmVO(BusinessDevelopmentManager bdm) {
		BDMVO bdmVO = new BDMVO();
		if (bdm != null) {
			bdmVO.setBdmId(bdm.getBdmId());
			bdmVO.setBdmName(bdm.getBdmName());
			bdmVO.setBdmDisplayName(bdm.getBdmDisplayName());
			bdmVO.setActiveFrom(bdm.getActiveFrom());
			bdmVO.setActiveUntil(bdm.getActiveUntil());
			bdmVO.setLinkedToBusinessUnit(bdm.getLinkedToBusinessUnit());
			bdmVO.setLinkedToRegion(bdm.getLinkedToRegion());
			bdmVO.setActive(bdm.isActive());
		}

		return bdmVO;

	}

}
