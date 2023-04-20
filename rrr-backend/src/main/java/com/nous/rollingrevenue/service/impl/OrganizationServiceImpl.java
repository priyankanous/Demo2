package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	@Transactional
	public void addOrganization(OrganizationVO organizationVO) {
		Organization organization = OrganizationConverter.convertOrganizationVOToOrganization(organizationVO);
		organizationRepository.save(organization);
	}

	@Override
	public OrganizationVO getOrganization(Long id) {
		Optional<Organization> orgOptional = organizationRepository.findById(id);

		if (orgOptional.isPresent()) {
			return OrganizationConverter.convertOrganizationToOrganizationVO(orgOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);

	}

	@Override
	@Transactional
	public void deleteOrganization(Long id) {
		Optional<Organization> orgOptional = organizationRepository.findById(id);

		if (orgOptional.isPresent()) {
			organizationRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
		}
	}

	@Override
	public List<OrganizationVO> getAllOrganization() {
		List<OrganizationVO> organizationVOs = new ArrayList<>();
		organizationRepository.findAll().stream().forEach(organization -> {
			organizationVOs.add(OrganizationConverter.convertOrganizationToOrganizationVO(organization));
		});
		return organizationVOs;
	}

	@Override
	@Transactional
	public void updateOrganization(Long id, OrganizationVO organizationVO) {
		Organization organization = organizationRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		organization.setOrgName(organizationVO.getOrgName());
		organization.setOrgDisplayName(organizationVO.getOrgDisplayName());
		organizationRepository.save(organization);
	}

	@Override
	public List<OrganizationVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<OrganizationVO> organizationVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<Organization> pageResult = organizationRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				organizationVOs.add(OrganizationConverter.convertOrganizationToOrganizationVO(e));
			});
			return organizationVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long id) {
		Organization organization = organizationRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		organization.setActive(!organization.isActive());
		organizationRepository.save(organization);
	}

}
