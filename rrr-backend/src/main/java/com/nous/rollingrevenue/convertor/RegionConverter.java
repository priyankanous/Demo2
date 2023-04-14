package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.vo.RegionVO;

@Component
public class RegionConverter {

	/**
	 * Convert RegionVO to Region
	 * 
	 * @param RegionVO
	 * @return Region
	 */

	public static Region convertRegionVOToRegion(RegionVO regionVO) {
		Region region = new Region();
		if (regionVO != null) {
			region.setRegionId(regionVO.getRegionId());
			region.setRegionName(regionVO.getRegionName());
			region.setRegionDisplayName(regionVO.getRegionDisplayName());
		}
		return region;
	}

	/**
	 * Convert Region to RegionVO
	 * 
	 * @param Region
	 * @return RegionVO
	 */

	public static RegionVO convertRegionToRegionVO(Region region) {
		RegionVO regionVO = new RegionVO();
		if (region != null) {
			regionVO.setRegionId(region.getRegionId());
			regionVO.setRegionName(region.getRegionName());
			regionVO.setRegionDisplayName(region.getRegionDisplayName());
			regionVO.setActive(region.isActive());
		}
		return regionVO;
	}

}
