package com.nous.rollingrevenue.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.vo.BDMVO;
import com.nous.rollingrevenue.vo.BusinessUnitVO;
import com.nous.rollingrevenue.vo.RegionVO;

@Component
public class BusinessDevelopmentManagerConverter {

	private BusinessDevelopmentManagerConverter() {
		super();
	}

	/**
	 * Convert BDM VO to BDM
	 * 
	 * @param BDM Vo
	 * @return BDM Jpa bean
	 */
	public static BusinessDevelopmentManager convertBdmVOToBdm(BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = new BusinessDevelopmentManager();
		if (bdmVO != null) {
			bdm.setBdmId(bdmVO.getBdmId());
			bdm.setBdmName(bdmVO.getBdmName());
			bdm.setBdmDisplayName(bdmVO.getBdmDisplayName());
			bdm.setActiveFrom(bdmVO.getActiveFrom());
			bdm.setActiveUntil(bdmVO.getActiveUntil());
			List<BusinessUnit> businessUnits = new ArrayList<>();
			bdmVO.getBusinessUnits().stream().forEach(businessUnitVO -> businessUnits
					.add(BusinessUnitConverter.convertBusinessUnitVOToBusinessUnit(businessUnitVO)));
			bdm.setBusinessUnits(businessUnits);
			List<Region> regions = new ArrayList<>();
			bdmVO.getRegions().stream()
					.forEach(regionVO -> regions.add(RegionConverter.convertRegionVOToRegion(regionVO)));
			bdm.setRegions(regions);
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
			List<BusinessUnitVO> businessUnitVOs = new ArrayList<>();
			bdm.getBusinessUnits().stream().forEach(businessUnit -> businessUnitVOs
					.add(BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(businessUnit)));
			bdmVO.setBusinessUnits(businessUnitVOs);
			List<RegionVO> regionVOs = new ArrayList<>();
			bdm.getRegions().stream().forEach(region -> regionVOs.add(RegionConverter.convertRegionToRegionVO(region)));
			bdmVO.setRegions(regionVOs);
			bdmVO.setActive(bdm.isActive());
		}

		return bdmVO;

	}

}
