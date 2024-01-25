package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.UserEntity;
import com.nous.rollingrevenue.vo.UserUpdateVO;
import com.nous.rollingrevenue.vo.UserVO;

@Component
public class UserConverter {

	private UserConverter() {
		super();
	}

	/**
	 * Convert UserVO to UserEntity
	 * 
	 * @param UserVO
	 * @return UserEntity
	 */
	public static UserEntity convertUserEntityVOToUserEntity(UserVO userVO) {
		UserEntity userEntity = new UserEntity();
		if (userVO != null) {
			userEntity.setUserId(userVO.getUserId());
			userEntity.setUserName(userVO.getUserName());
			userEntity.setEmployeeId(userVO.getEmployeeId());
			userEntity.setEmailId(userVO.getEmailId());
		}
		return userEntity;
	}

	/**
	 * Convert UserEntity to UserUpdateVO
	 * 
	 * @param UserEntity
	 * @return UserUpdateVO
	 */
	public static UserUpdateVO convertUserEntityToUserUpdateVO(UserEntity userEntity) {
		UserUpdateVO userUpdateVO = new UserUpdateVO();
		if (userEntity != null) {
			userUpdateVO.setUserName(userEntity.getUserName());
			userUpdateVO.setUserId(userEntity.getUserId());
			userUpdateVO.setEmployeeId(userEntity.getEmployeeId());
			userUpdateVO.setEmailId(userEntity.getEmailId());
			userUpdateVO.setPassword(userEntity.getPassword());
			userUpdateVO.setActive(userEntity.isActive());
		}
		return userUpdateVO;
	}

}