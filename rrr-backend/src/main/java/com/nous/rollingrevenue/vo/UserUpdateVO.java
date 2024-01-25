package com.nous.rollingrevenue.vo;
 
import java.io.Serializable;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
 
@Data
public class UserUpdateVO implements Serializable {
 
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
 
	@NotBlank(message = "Password cannot be null or empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "password must contain minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:")
	private String password;
 
	private boolean isActive;
 
}