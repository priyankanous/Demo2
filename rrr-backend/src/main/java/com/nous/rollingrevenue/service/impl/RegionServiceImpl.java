package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.RegionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.repository.AccountRepository;
import com.nous.rollingrevenue.repository.RegionRepository;
import com.nous.rollingrevenue.service.RegionService;
import com.nous.rollingrevenue.vo.RegionVO;

@Service
@Transactional(readOnly = true)
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<RegionVO> getAllRegions() {
		List<RegionVO> regionsVO = new ArrayList<>();
		regionRepository.findAll().stream()
				.forEach(region -> regionsVO.add(RegionConverter.convertRegionToRegionVO(region)));
		return regionsVO;
	}

	@Override
	@Transactional
	public void saveRegion(RegionVO regionVO) {
		regionRepository.save(RegionConverter.convertRegionVOToRegion(regionVO));
	}

	@Override
	@Transactional
	public void deleteRegionById(Long regionId) {
		regionRepository.findById(regionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + regionId));
		regionRepository.deleteById(regionId);
	}

	@Override
	public RegionVO getRegionById(Long regionId) {
		Region region = regionRepository.findById(regionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + regionId));
		return RegionConverter.convertRegionToRegionVO(region);
	}

	@Override
	@Transactional
	public void updateRegion(Long regionId, RegionVO regionVO) {
		Region region = regionRepository.findById(regionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + regionId));
		region.setRegionName(regionVO.getRegionName());
		region.setRegionDisplayName(regionVO.getRegionDisplayName());
		regionRepository.save(region);
	}

	@Override
	public List<RegionVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<RegionVO> regionVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<Region> pageResult = regionRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				regionVOs.add(RegionConverter.convertRegionToRegionVO(e));
			});
			return regionVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long regionId) {
		Region region = regionRepository.findById(regionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + regionId));
		region.setActive(!region.isActive());
		regionRepository.save(region);
	}

	@Override
	public List<RegionVO> getRegionByAccountId(Long accountId) {
		List<RegionVO> list = new ArrayList<>();
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + accountId));
		List<Region> regions = account.getRegions();
		for (Region region : regions) {
			list.add(RegionConverter.convertRegionToRegionVO(region));
		}
		return list;
	}

}
