package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nous.rollingrevenue.model.UserEntity;

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
	private String emailId;

	@NotBlank(message = "Employee Id cannot be null or empty")
	private Long employeeId;

	private boolean isActive;

}
