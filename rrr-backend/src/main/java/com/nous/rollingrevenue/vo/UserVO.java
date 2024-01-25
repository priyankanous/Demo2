package com.nous.rollingrevenue.vo;
 
import java.io.Serializable;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
 
@Data
public class UserVO implements Serializable {
 
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
 
	private Long userId;
 
	@NotBlank(message = "Name cannot be null or empty")
	private String userName;
 
	@NotBlank(message = "Email Id cannot be null or empty")
	@Email(message = "Enter a valid Email Id")
	private String emailId;
 
	@NotBlank(message = "Employee Id cannot be null or empty")
	private String employeeId;
 
	private boolean isActive;
 
}