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

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.FinancialYearConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.model.BDMMeeting;
import com.nous.rollingrevenue.model.CurrencyEntity;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.FortnightlyMeeting;
import com.nous.rollingrevenue.model.GlobalMonthlyLeaveLossFactor;
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.model.RevenueEntry;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.repository.BDMMeetingRepository;
import com.nous.rollingrevenue.repository.CurrencyRepository;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.FortnightlyMeetingRepository;
import com.nous.rollingrevenue.repository.GlobalMonthlyLeaveLossFactorRepository;
import com.nous.rollingrevenue.repository.HolidayCalendarRepository;
import com.nous.rollingrevenue.repository.RevenueEntryRespository;
import com.nous.rollingrevenue.service.FinancialYearService;
import com.nous.rollingrevenue.vo.FinancialYearVO;

@Service
@Transactional(readOnly = true)
public class FinancialYearServiceImpl implements FinancialYearService {

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private GlobalMonthlyLeaveLossFactorRepository leaveLossFactorRepository;

	@Autowired
	private HolidayCalendarRepository holidayCalendarRepository;

	@Autowired
	private BDMMeetingRepository bdmMeetingRepository;

	@Autowired
	private FortnightlyMeetingRepository fortnightlyMeetingRepository;

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;

	@Autowired
	private RevenueEntryRespository revenueEntryRespository;

	@Override
	public List<FinancialYearVO> getAllFinancialYear() {
		List<FinancialYearVO> financialYearVO = new ArrayList<>();
		financialYearRepository.findAll().stream().forEach(financialYear -> financialYearVO
				.add(FinancialYearConverter.convertFinancialYearToFinancialYearVO(financialYear)));
		return financialYearVO;
	}

	@Override
	@Transactional
	public void saveFinancialYear(FinancialYearVO financialYearVO) {
		financialYearRepository.save(FinancialYearConverter.convertFinancialYearVOToFinancialYear(financialYearVO));
	}

	@Override
	@Transactional
	public void deleteFinancialYearById(Long financialYearId) {
		List<CurrencyEntity> currencyList = currencyRepository.findByFinancialYearId(financialYearId);
		if (!currencyList.isEmpty()) {
			throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
		}
		List<GlobalMonthlyLeaveLossFactor> leaveLossFactorByLocation = leaveLossFactorRepository
				.getLeaveLossFactorByLocation(financialYearId);
		if (!leaveLossFactorByLocation.isEmpty()) {
			throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
		}
		List<HolidayCalendar> holidayList = holidayCalendarRepository.findByFinancialYearId(financialYearId);
		if (!holidayList.isEmpty()) {
			throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
		}
		List<BDMMeeting> bdmMeetingList = bdmMeetingRepository.findByFinancialYearId(financialYearId);
		if (!bdmMeetingList.isEmpty()) {
			throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
		}
		List<FortnightlyMeeting> fortnightlyMeetingList = fortnightlyMeetingRepository
				.findByFinancialYearId(financialYearId);
		if (!fortnightlyMeetingList.isEmpty()) {
			throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository
				.findByFinancialYearId(financialYearId);
		if (!annualTargetEntryList.isEmpty()) {
			throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
		}
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByFinancialYearId(financialYearId);
		if (!revenueEntryList.isEmpty()) {
			throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
		}
		Optional<FinancialYear> findById = financialYearRepository.findById(financialYearId);
		if (findById.isEmpty()) {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId);
		} else {
			financialYearRepository.deleteById(financialYearId);
		}
	}

	@Override
	public FinancialYearVO getFinancialYearById(Long financialYearId) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		return FinancialYearConverter.convertFinancialYearToFinancialYearVO(financialYear);
	}

	@Override
	@Transactional
	public void updateFinancialYear(Long financialYearId, FinancialYearVO financialYearVO) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		financialYear.setFinancialYearName(financialYearVO.getFinancialYearName());
		financialYear.setFinancialYearCustomName(financialYearVO.getFinancialYearCustomName());
		financialYear.setStartingFrom(financialYearVO.getStartingFrom());
		financialYear.setEndingOn(financialYearVO.getEndingOn());
		financialYearRepository.save(financialYear);
	}

	@Override
	public List<FinancialYearVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<FinancialYearVO> financialYearVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<FinancialYear> pageResult = financialYearRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				financialYearVOs.add(FinancialYearConverter.convertFinancialYearToFinancialYearVO(e));
			});
			return financialYearVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long financialYearId) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		List<CurrencyEntity> currencyList = currencyRepository.findByFinancialYearId(financialYearId);
		for (CurrencyEntity currency : currencyList) {
			if (financialYear.isActive() && currency.isActive()) {
				throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
			}
		}
		List<GlobalMonthlyLeaveLossFactor> leaveLossFactorByLocation = leaveLossFactorRepository
				.getLeaveLossFactorByLocation(financialYearId);
		for (GlobalMonthlyLeaveLossFactor leaveLossFactor : leaveLossFactorByLocation) {
			if (financialYear.isActive() && leaveLossFactor.isActive()) {
				throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
			}
		}
		List<HolidayCalendar> holidayList = holidayCalendarRepository.findByFinancialYearId(financialYearId);
		for (HolidayCalendar holiday : holidayList) {
			if (financialYear.isActive() && holiday.isActive()) {
				throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
			}
		}
		List<BDMMeeting> bdmMeetingList = bdmMeetingRepository.findByFinancialYearId(financialYearId);
		for (BDMMeeting bdmMeeting : bdmMeetingList) {
			if (financialYear.isActive() && bdmMeeting.isActive()) {
				throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
			}
		}
		isActiveValidationForFinancialYear(financialYear, financialYearId);
		financialYear.setActive(!financialYear.isActive());
		financialYearRepository.save(financialYear);
	}

	private void isActiveValidationForFinancialYear(FinancialYear financialYear, Long financialYearId) {
		List<FortnightlyMeeting> fortnightlyMeetingList = fortnightlyMeetingRepository
				.findByFinancialYearId(financialYearId);
		for (FortnightlyMeeting fortnightlyMeeting : fortnightlyMeetingList) {
			if (financialYear.isActive() && fortnightlyMeeting.isActive()) {
				throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
			}
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository
				.findByFinancialYearId(financialYearId);
		for (AnnualTargetEntry annualTargetEntry : annualTargetEntryList) {
			if (financialYear.isActive() && annualTargetEntry.isActive()) {
				throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
			}
		}
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByFinancialYearId(financialYearId);
		for (RevenueEntry revenueEntry : revenueEntryList) {
			if (financialYear.isActive() && revenueEntry.isActive()) {
				throw new RecordNotFoundException(Constants.FINANCIALYEAR_IS_ALREADY_LINKED);
			}
		}
	}

	@Override
	public FinancialYearVO getFinancialYearByName(String financialYear) {
		FinancialYear findFinancialYearByName = financialYearRepository.findByFinancialYearName(financialYear)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYear));
		return FinancialYearConverter.convertFinancialYearToFinancialYearVO(findFinancialYearByName);
	}

}
