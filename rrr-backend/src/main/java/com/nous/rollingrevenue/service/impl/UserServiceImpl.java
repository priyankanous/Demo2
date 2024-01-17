package com.nous.rollingrevenue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.repository.UserRepository;
import com.nous.rollingrevenue.service.UserService;
import com.nous.rollingrevenue.vo.UserVO;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserVO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(UserVO userVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserByEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserVO getUserByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO getUserByEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
