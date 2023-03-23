package com.nous.rollingrevenue.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Organization;
import com.nous.rollingrevenue.repository.OrganizationRepository;
import com.nous.rollingrevenue.service.OrganizationService;
import com.nous.rollingrevenue.vo.OrganizationVO;


@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public Organization addOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

	@Override
	@Cacheable(value = "organizations", key = "#id") 
	public Organization getOrganization(Long id) {
		Optional<Organization> orgOptional = organizationRepository.findById(id);

		if (orgOptional.isPresent()) {
			return orgOptional.get();
		}
		throw new RecordNotFoundException("Organization not found for id:" + id);

	}

	@Override
	@Transactional
	@CacheEvict(value = "organizations", key = "#id") 
	public void deleteOrganization(Long id) {
		Optional<Organization> orgOptional = organizationRepository.findById(id);

		if (orgOptional.isPresent()) {
			organizationRepository.deleteById(id);
		} else {
		throw new RecordNotFoundException("Organization not found for id:" + id);
		}
	}

	@Override
	@Transactional
	public List<Organization> getAllOrganization() {
		return organizationRepository.findAll();
	}

	@Override
	@Transactional
	@CachePut(value = "organizations", key = "id") 
	public Organization updateOrganization(Long id, OrganizationVO organizationVO) {
		Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Organization not found for id:" + id));
		organization.setorgDisplayName(organizationVO.getorgDisplayName());
		organization.setorgName(organizationVO.getorgName());
		organization.setId(organizationVO.getId());
		return organizationRepository.save(organization);
	}



}
