package com.nous.rollingrevenue.service.impl;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Rohit");

	}
	
	// ------------------ Use below code for spring security --------------------------

	  /* public User getCurrentAuditor() {

	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	  if (authentication == null || !authentication.isAuthenticated()) {
	   return null;
	  }

	  return ((MyUserDetails) authentication.getPrincipal()).getUser();
	 
	}*/

}
