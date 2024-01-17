package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.UserVO;

public interface UserService {

	/**
	 * Get all the Users
	 * 
	 * @return List of all Users in the database
	 */
	public List<UserVO> getAllUsers();

	/**
	 * Add an User to the database
	 * 
	 * @param UserVO
	 * 
	 */
	public void saveUser(UserVO userVO);

	/**
	 * Delete an User record by given Employee Id
	 * 
	 * @param employeeId The employeeId of the User to be deleted. Throws
	 *                   {@link RecordNotFoundException} if no match is found
	 */
	public void deleteUserByEmployeeId(Long employeeId);

	/**
	 * Get the User details by given Id
	 * 
	 * @param userId The userId for retrieving the details
	 * @return The User details matching the User id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public UserVO getUserByUserId(Long userId);

	/**
	 * Get the User details by given EmployeeId
	 * 
	 * @param employeeId The employeeId for retrieving the details of user
	 * @return The User details matching the employee id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public UserVO getUserByEmployeeId(Long employeeId);

}
