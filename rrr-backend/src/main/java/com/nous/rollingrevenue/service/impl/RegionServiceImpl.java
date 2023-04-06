package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.repository.RegionRepository;
import com.nous.rollingrevenue.service.RegionService;
import com.nous.rollingrevenue.vo.RegionVO;

@Service
@Transactional(readOnly = true)
public class RegionServiceImpl implements RegionService {
	
	
	@Autowired
	private RegionRepository regionRepository;


	@Override
	public List<RegionVO> getAllRegions() {
		List<RegionVO> regionsVO = new ArrayList<>();
		regionRepository.findAll().stream().forEach(
				region -> regionsVO.add(RegionConverter.convertRegionToRegionVO(region)));
		return regionsVO;
	}


	@Override
	@Transactional
	public RegionVO saveRegion(RegionVO regionVO) {
		Region region = regionRepository.save(RegionConverter.convertRegionVOToRegion(regionVO));	
		return RegionConverter.convertRegionToRegionVO(region);
	}


	@Override
	@Transactional
	@CacheEvict(value = "regions", key= "#regionId")
	public void deleteRegionById(Long regionId) {
		regionRepository.findById(regionId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + regionId));
		regionRepository.deleteById(regionId);	
	}


	@Override
	@Cacheable(value = "regions", key = "#regionId")
	public RegionVO getRegionById(Long regionId) {
		Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + regionId));
		return RegionConverter.convertRegionToRegionVO(region);
	}


	@Override
	@Transactional
	@CachePut(value = "regions", key = "#regionId")
	public RegionVO updateRegion(Long regionId, RegionVO regionVO) {
		Region region = regionRepository.findById(regionId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + regionId));
		region.setRegionName(regionVO.getRegionName());
		region.setRegionDisplayName(regionVO.getRegionDisplayName());
		return RegionConverter.convertRegionToRegionVO(regionRepository.save(region));
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

}
