package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.OrganizationConverter;
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
	public OrganizationVO addOrganization(OrganizationVO organizationVO) {
		Organization organization = OrganizationConverter.convertOrganizationVOToOrganization(organizationVO);
		return OrganizationConverter.convertOrganizationToOrganizationVO(organizationRepository.save(organization));
	}

	@Override
	@Cacheable(value = "organizations", key = "#id")
	public OrganizationVO getOrganization(Long id) {
		Optional<Organization> orgOptional = organizationRepository.findById(id);

		if (orgOptional.isPresent()) {
			return OrganizationConverter.convertOrganizationToOrganizationVO(orgOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);

	}

	@Override
	@Transactional
	@CacheEvict(value = "organizations", key = "#id")
	public void deleteOrganization(Long id) {
		Optional<Organization> orgOptional = organizationRepository.findById(id);

		if (orgOptional.isPresent()) {
			organizationRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
		}
	}

	@Override
	@Transactional
	public List<OrganizationVO> getAllOrganization() {
		List<OrganizationVO> organizationVOs = new ArrayList<>();
		organizationRepository.findAll().stream().forEach(organization -> {
			organizationVOs.add(OrganizationConverter.convertOrganizationToOrganizationVO(organization));
		});
		return organizationVOs;
	}

	@Override
	@Transactional
	@CachePut(value = "organizations", key = "id")
	public OrganizationVO updateOrganization(Long id, OrganizationVO organizationVO) {
		Organization organization = organizationRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		organization.setorgDisplayName(organizationVO.getorgDisplayName());
		organization.setorgName(organizationVO.getorgName());
		organization.setId(organizationVO.getId());
		return OrganizationConverter.convertOrganizationToOrganizationVO(organizationRepository.save(organization));
	}

}
