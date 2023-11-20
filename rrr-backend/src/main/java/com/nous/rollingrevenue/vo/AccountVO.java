package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long accountId;

	@NotBlank(message = "AccountName cannot be null or empty")
	private String accountName;

	private RegionVO regions;

	private boolean isActive;

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
