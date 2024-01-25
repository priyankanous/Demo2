package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.UserUpdateVO;
import com.nous.rollingrevenue.vo.UserVO;

import jakarta.validation.Valid;

public interface UserService {

	/**
	 * Get all the Users
	 * 
	 * @return List of all Users in the database
	 */
	public List<UserUpdateVO> getAllUsersEntity();

	/**
	 * Add an User to the database
	 * 
	 * @param UserVO
	 * 
	 */
	public void saveUserEntity(UserVO userVO);

	/**
	 * Delete an User record by given Employee Id
	 * 
	 * @param employeeId The employeeId of the User to be deleted. Throws
	 *                   {@link RecordNotFoundException} if no match is found
	 */
	public void deleteUserEntityByEmployeeId(String employeeId);

	/**
	 * Get the User details by given Id
	 * 
	 * @param userId The userId for retrieving the details
	 * @return The User details matching the User id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public UserUpdateVO getUserEntityByUserId(Long userId);

	/**
	 * Get the User details by given EmployeeId
	 * 
	 * @param employeeId The employeeId for retrieving the details of user
	 * @return The User details matching the employee id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public UserUpdateVO getUserEntityByEmployeeId(String employeeId);

	/**
	 * Update an User details to the database by given user Id
	 * 
	 * @param UserId, UserVO
	 * 
	 */
	public void updateUserEntityByUserId(Long userId, @Valid UserUpdateVO userUpdateVO);

	/**
	 * Delete an User record by given User Id
	 * 
	 * @param userId The userId of the User to be deleted. Throws
	 *               {@link RecordNotFoundException} if no match is found
	 */
	public void deleteUserEntityByUserId(Long userId);

}