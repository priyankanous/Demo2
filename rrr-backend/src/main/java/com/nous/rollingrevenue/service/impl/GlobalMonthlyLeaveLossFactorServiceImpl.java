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
import com.nous.rollingrevenue.convertor.LeaveLossFactorConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.GlobalMonthlyLeaveLossFactor;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.GlobalMonthlyLeaveLossFactorRepository;
import com.nous.rollingrevenue.service.GlobalMonthlyLeaveLossFactorService;
import com.nous.rollingrevenue.vo.GlobalMonthlyLeaveLossFactorVO;

@Service
public class GlobalMonthlyLeaveLossFactorServiceImpl implements GlobalMonthlyLeaveLossFactorService {

	@Autowired
	private GlobalMonthlyLeaveLossFactorRepository globalMonthlyLeaveLossFactorRepository;

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Override
	@Transactional
	public void addLeaveLossFactor(GlobalMonthlyLeaveLossFactorVO leaveLossFactorVO) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = LeaveLossFactorConverter
				.convertLeaveLossFactorVOToLeaveLossFactor(leaveLossFactorVO);
		FinancialYear financialYear = financialYearRepository
				.findById(leaveLossFactorVO.getFinancialYear().getFinancialYearId()).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "FinancialYear not exist"));
		leaveLossFactor.setFinancialYear(financialYear);
		globalMonthlyLeaveLossFactorRepository.save(leaveLossFactor);
	}

	@Override
	@Transactional
	public void updateLeaveLossFactor(Long id, GlobalMonthlyLeaveLossFactorVO leaveLossFactorVO) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = globalMonthlyLeaveLossFactorRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		leaveLossFactor.setMonth(leaveLossFactorVO.getMonth());
		leaveLossFactor.setOffShore(leaveLossFactorVO.getOffShore());
		leaveLossFactor.setOnSite(leaveLossFactorVO.getOnSite());
		FinancialYear financialYear = financialYearRepository
				.findById(leaveLossFactorVO.getFinancialYear().getFinancialYearId()).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "FinancialYear not exist"));
		leaveLossFactor.setFinancialYear(financialYear);
		globalMonthlyLeaveLossFactorRepository.save(leaveLossFactor);
	}

	@Override
	public GlobalMonthlyLeaveLossFactorVO getLeaveLossFactorById(Long id) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = globalMonthlyLeaveLossFactorRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		return LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor);

	}

	@Override
	@Transactional
	public void deleteLeaveLossFactor(Long id) {
		Optional<GlobalMonthlyLeaveLossFactor> findById = globalMonthlyLeaveLossFactorRepository.findById(id);
		if (findById.isEmpty()) {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
		} else {
			globalMonthlyLeaveLossFactorRepository.deleteById(id);
		}
	}

	@Override
	public List<GlobalMonthlyLeaveLossFactorVO> getLeaveLossFactors() {
		List<GlobalMonthlyLeaveLossFactorVO> leaveLossFactorVOs = new ArrayList<>();
		globalMonthlyLeaveLossFactorRepository.findAll().stream().forEach(leaveLossFactor -> {
			leaveLossFactorVOs.add(LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor));
		});
		return leaveLossFactorVOs;
	}

	@Override
	public List<GlobalMonthlyLeaveLossFactorVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<GlobalMonthlyLeaveLossFactorVO> globalMonthlyLeaveLossFactorVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<GlobalMonthlyLeaveLossFactor> pageResult = globalMonthlyLeaveLossFactorRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				globalMonthlyLeaveLossFactorVOs
						.add(LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(e));
			});
			return globalMonthlyLeaveLossFactorVOs;
		}
		return Collections.emptyList();
	}

	@Override
	public List<GlobalMonthlyLeaveLossFactorVO> getLeaveLossFactorByFinancialYear(String financialYear) {
		List<GlobalMonthlyLeaveLossFactorVO> leaveLossFactorVOs = new ArrayList<>();
		Optional<FinancialYear> findByFinancialYearName = financialYearRepository
				.findByFinancialYearName(financialYear);
		if (findByFinancialYearName.isPresent()) {
			findByFinancialYearName.get().getLeaveLossFactors().stream().forEach(leaveLossFactor -> {
				leaveLossFactorVOs
						.add(LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor));
			});
		}
		return leaveLossFactorVOs;
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long id) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = globalMonthlyLeaveLossFactorRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		Optional<FinancialYear> findById = financialYearRepository
				.findById(leaveLossFactor.getFinancialYear().getFinancialYearId());
		if (findById.isPresent()) {
			FinancialYear financialYear = findById.get();
			if (!financialYear.isActive() && !leaveLossFactor.isActive()) {
				throw new RecordNotFoundException(
						"FinancialYear is not active and its already linked to GlobalMonthlyLeaveLossFactor");
			}
		}
		leaveLossFactor.setActive(!leaveLossFactor.isActive());
		globalMonthlyLeaveLossFactorRepository.save(leaveLossFactor);
	}

	@Override
	@Transactional
	public void saveListOfGlobalLeaveLossFactor(List<GlobalMonthlyLeaveLossFactorVO> globalMonthlyLeaveLossFactorVOs) {
		List<GlobalMonthlyLeaveLossFactor> leaveLossFactor = new ArrayList<>();
		globalMonthlyLeaveLossFactorVOs.stream().forEach(globalMonthlyLeaveLossFactorVO -> leaveLossFactor.add(
				LeaveLossFactorConverter.convertLeaveLossFactorVOToLeaveLossFactor(globalMonthlyLeaveLossFactorVO)));
		globalMonthlyLeaveLossFactorRepository.saveAll(leaveLossFactor);

	}
}
