package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Location;
import com.nous.rollingrevenue.vo.LocationVO;

@Component
public class LocationConverter {

	/**
	 * Convert LocationVO to Location
	 * 
	 * @param LocationVO
	 * @return Location Jpa bean
	 */
	public static Location convertLocationVOToLocation(LocationVO locationVO) {
		Location location = new Location();
		if (locationVO != null) {
			if (locationVO.getLocationId() != null) {
				location.setLocationId(locationVO.getLocationId());
			}
			location.setLocationName(locationVO.getLocationName());
			location.setLocationDisplayName(locationVO.getLocationDisplayName());
		}
		return location;
	}

	/**
	 * Convert Location to LocationVO
	 * 
	 * @param Location location
	 * @return LocationVO
	 */
	public static LocationVO convertLocationToLocationVO(Location location) {
		LocationVO locationVO = new LocationVO();
		if (location != null) {
			locationVO.setLocationId(location.getLocationId());
			locationVO.setLocationName(location.getLocationName());
			locationVO.setLocationDisplayName(location.getLocationDisplayName());
		}
		return locationVO;
	}

}
