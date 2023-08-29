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
import com.nous.rollingrevenue.convertor.CocPracticeConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.repository.BusinessUnitRepository;
import com.nous.rollingrevenue.repository.CocPracticeRepository;
import com.nous.rollingrevenue.repository.RevenueResourceEntryRepository;
import com.nous.rollingrevenue.service.CocPracticeService;
import com.nous.rollingrevenue.vo.CocPracticeVO;

@Service
@Transactional(readOnly = true)
public class CocPracticeServiceImpl implements CocPracticeService {

	@Autowired
	CocPracticeRepository cocpracticeRepository;

	@Autowired
	BusinessUnitRepository businessUnitRepository;

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;

	@Autowired
	private RevenueResourceEntryRepository revenueResourceEntryRepository;

	@Override
	@Transactional
	public void addCocPractice(CocPracticeVO cocpracticeVO) {
		CocPractice cocPractice = CocPracticeConverter.convertCocPracticeVOToCocPractice(cocpracticeVO);
		BusinessUnit businessUnit = businessUnitRepository.findById(cocpracticeVO.getBusinessUnit().getBusinessUnitId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "BusinessUnit"));
		cocPractice.setBusinessUnit(businessUnit);
		cocpracticeRepository.save(cocPractice);
	}

	@Override
	public CocPracticeVO getCocPractice(Long id) {
		Optional<CocPractice> cocOptional = cocpracticeRepository.findById(id);

		if (cocOptional.isPresent()) {
			return CocPracticeConverter.convertCocPracticeToCocPracticeVO(cocOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);

	}

	@Override
	@Transactional
	public void deleteCocPractice(Long id) {
		Optional<CocPractice> cocOptional = cocpracticeRepository.findById(id);
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findByCocPracticeId(id);
		if (!annualTargetEntryList.isEmpty()) {
			throw new RecordNotFoundException(
					"CocPractice is already linked to AnnualTargetEntry or RevenueResourceEntry");
		}
		List<RevenueResourceEntry> revenueResourceList = revenueResourceEntryRepository.findByCocPracticeId(id);
		if (!revenueResourceList.isEmpty()) {
			throw new RecordNotFoundException(
					"CocPractice is already linked to AnnualTargetEntry or RevenueResourceEntry");
		}
		if (cocOptional.isPresent()) {
			cocpracticeRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
		}
	}

	@Override
	public List<CocPracticeVO> getAllCocPractice() {
		List<CocPracticeVO> cocPracticeVOs = new ArrayList<>();
		cocpracticeRepository.findAll().stream().forEach(cocPractice -> {
			cocPracticeVOs.add(CocPracticeConverter.convertCocPracticeToCocPracticeVO(cocPractice));
		});
		return cocPracticeVOs;
	}

	@Override
	@Transactional
	public void updateCocPractice(Long id, CocPracticeVO cocpracticeVO) {
		CocPractice cocpractice = cocpracticeRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		cocpractice.setCocPracticeDisplayName(cocpracticeVO.getCocPracticeDisplayName());
		cocpractice.setCocPracticeName(cocpracticeVO.getCocPracticeName());
		BusinessUnit businessUnit = businessUnitRepository.findById(cocpracticeVO.getBusinessUnit().getBusinessUnitId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "BusinessUnit"));
		cocpractice.setBusinessUnit(businessUnit);
		cocpracticeRepository.save(cocpractice);
	}

	@Override
	public List<CocPracticeVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<CocPracticeVO> cocPracticeVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<CocPractice> pageResult = cocpracticeRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				cocPracticeVOs.add(CocPracticeConverter.convertCocPracticeToCocPracticeVO(e));
			});
			return cocPracticeVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long id) {
		CocPractice cocpractice = cocpracticeRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		Optional<BusinessUnit> bu = businessUnitRepository.findById(cocpractice.getBusinessUnit().getBusinessUnitId());
		if (bu.isPresent()) {
			BusinessUnit businessUnit = bu.get();
			if (!businessUnit.isActive() && !cocpractice.isActive()) {
				throw new RecordNotFoundException("BU is not active and its already linked to CocPractice");
			}
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findByCocPracticeId(id);
		for (AnnualTargetEntry targetEntry : annualTargetEntryList) {
			if (cocpractice.isActive() && targetEntry.isActive()) {
				throw new RecordNotFoundException(
						"CocPractice is already linked to AnnualTargetEntry or RevenueResourceEntry");
			}
		}
		List<RevenueResourceEntry> revenueResourceList = revenueResourceEntryRepository.findByCocPracticeId(id);
		for (RevenueResourceEntry revenueResource : revenueResourceList) {
			if (cocpractice.isActive() && revenueResource.isActive()) {
				throw new RecordNotFoundException(
						"CocPractice is already linked to AnnualTargetEntry or RevenueResourceEntry");
			}
		}
		cocpractice.setActive(!cocpractice.isActive());
		cocpracticeRepository.save(cocpractice);
	}
}
