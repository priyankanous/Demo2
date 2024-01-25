package com.nous.rollingrevenue.service.impl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.UserConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.UserEntity;
import com.nous.rollingrevenue.repository.UserRepository;
import com.nous.rollingrevenue.service.UserService;
import com.nous.rollingrevenue.vo.UserUpdateVO;
import com.nous.rollingrevenue.vo.UserVO;
 
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
 
	@Autowired
	private UserRepository userRepository;
 
	@Override
	public List<UserUpdateVO> getAllUsersEntity() {
		List<UserUpdateVO> userUpdateVO = new ArrayList<>();
		userRepository.findAll().stream()
				.forEach(user -> userUpdateVO.add(UserConverter.convertUserEntityToUserUpdateVO(user)));
		return userUpdateVO;
	}
 
	@Override
	@Transactional
	public void saveUserEntity(UserVO userVO) {
		UserEntity userEntity = UserConverter.convertUserEntityVOToUserEntity(userVO);
		userEntity.setPassword("Nous@12345");
		userRepository.save(userEntity);
 
	}
 
	@Override
	@Transactional
	public void deleteUserEntityByEmployeeId(String employeeId) {
		Optional<UserEntity> findByEmployeeId = userRepository.findByEmployeeId(employeeId);
		if (findByEmployeeId.isPresent()) {
			userRepository.deleteUserEntityByEmployeeId(employeeId);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + employeeId);
		}
 
	}
 
	@Override
	@Transactional
	public void deleteUserEntityByUserId(Long userId) {
		Optional<UserEntity> findById = userRepository.findById(userId);
		if (findById.isPresent()) {
			userRepository.deleteById(userId);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + userId);
		}
	}
 
	@Override
	public UserUpdateVO getUserEntityByUserId(Long userId) {
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + userId));
		return UserConverter.convertUserEntityToUserUpdateVO(userEntity);
 
	}
 
	@Override
	public UserUpdateVO getUserEntityByEmployeeId(String employeeId) {
		UserEntity findUserEntityByEmployeeId = userRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + employeeId));
		return UserConverter.convertUserEntityToUserUpdateVO(findUserEntityByEmployeeId);
	}
 
	@Override
	@Transactional
	public void updateUserEntityByUserId(Long userId, UserUpdateVO userUpdateVO) {
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + userId));
		userEntity.setUserName(userUpdateVO.getUserName());
		userEntity.setEmployeeId(userUpdateVO.getEmployeeId());
		userEntity.setEmailId(userUpdateVO.getEmailId());
		userEntity.setPassword(userUpdateVO.getPassword());
		userRepository.save(userEntity);
	}
 
}