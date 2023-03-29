package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.AnnualTargetEntryConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.helper.ExcelHelper;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.service.AnnualTargetEntryService;
import com.nous.rollingrevenue.vo.AnnualTargetEntryVO;

@Service
@Transactional(readOnly = true)
public class AnnualTargetEntryServiceImpl implements AnnualTargetEntryService {

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;

	@Override
	public List<AnnualTargetEntryVO> getAllAnnualTargetEntry() {
		List<AnnualTargetEntryVO> annualTargetEntryVOs = new ArrayList<>();
		annualTargetEntryRepository.findAll().stream().forEach(annualTargetEntry -> annualTargetEntryVOs
				.add(AnnualTargetEntryConverter.convertAnnualTargetEntryToAnnualTargetEntryVO(annualTargetEntry)));
		return annualTargetEntryVOs;
	}

	@Override
	@Transactional
	public AnnualTargetEntryVO saveAnnualTargetEntry(AnnualTargetEntryVO annualTargetEntryVO) {
		AnnualTargetEntry annualTargetEntry = annualTargetEntryRepository
				.save(AnnualTargetEntryConverter.convertAnnualTargetEntryVOToAnnualTargetEntry(annualTargetEntryVO));
		return AnnualTargetEntryConverter.convertAnnualTargetEntryToAnnualTargetEntryVO(annualTargetEntry);
	}

	@Override
	@Transactional
	@CacheEvict(value = "annualtargetentry", key = "#annualTargetEntryId")
	public void deleteAnnualTargetEntryById(Long annualTargetEntryId) {
		annualTargetEntryRepository.findById(annualTargetEntryId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryId));
		annualTargetEntryRepository.deleteById(annualTargetEntryId);
	}

	@Override
	@Cacheable(value = "annualtargetentry", key = "#annualTargetEntryId")
	public AnnualTargetEntryVO getAnnualTargetEntryById(Long annualTargetEntryId) {
		AnnualTargetEntry annualTargetEntry = annualTargetEntryRepository.findById(annualTargetEntryId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryId));
		return AnnualTargetEntryConverter.convertAnnualTargetEntryToAnnualTargetEntryVO(annualTargetEntry);
	}

	@Override
	@Transactional
	@CachePut(value = "annualtargetentry", key = "#annualTargetEntryId")
	public AnnualTargetEntryVO updateAnnualTargetEntry(Long annualTargetEntryId,
			AnnualTargetEntryVO annualTargetEntryVO) {
		AnnualTargetEntry annualTargetEntry = annualTargetEntryRepository.findById(annualTargetEntryId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryId));
		annualTargetEntry.setFinancialYear(annualTargetEntryVO.getFinancialYear());
		annualTargetEntry.setBusinessUnit(annualTargetEntryVO.getBusinessUnit());
		annualTargetEntry.setStartegicBusinessUnit(annualTargetEntryVO.getStartegicBusinessUnit());
		annualTargetEntry.setStrategicBusinessUnitHead(annualTargetEntryVO.getStrategicBusinessUnitHead());
		annualTargetEntry.setLocation(annualTargetEntryVO.getLocation());
		annualTargetEntry.setRegion(annualTargetEntryVO.getRegion());
		annualTargetEntry.setAccount(annualTargetEntryVO.getAccount());
		annualTargetEntry.setBusinessType(annualTargetEntryVO.getBusinessType());
		annualTargetEntry.setCocPractice(annualTargetEntryVO.getCocPractice());
		annualTargetEntry.setBusinessDevelopmentManager(annualTargetEntryVO.getBusinessDevelopmentManager());
		annualTargetEntry.setQ1FYB(annualTargetEntryVO.getQ1FYB());
		annualTargetEntry.setQ1FYS(annualTargetEntryVO.getQ1FYS());
		annualTargetEntry.setQ1FYT(annualTargetEntryVO.getQ1FYT());
		annualTargetEntry.setQ2FYB(annualTargetEntryVO.getQ2FYB());
		annualTargetEntry.setQ2FYS(annualTargetEntryVO.getQ2FYS());
		annualTargetEntry.setQ2FYT(annualTargetEntryVO.getQ2FYT());
		annualTargetEntry.setQ3FYB(annualTargetEntryVO.getQ3FYB());
		annualTargetEntry.setQ3FYS(annualTargetEntryVO.getQ3FYS());
		annualTargetEntry.setQ3FYT(annualTargetEntryVO.getQ3FYT());
		annualTargetEntry.setQ4FYB(annualTargetEntryVO.getQ4FYB());
		annualTargetEntry.setQ4FYS(annualTargetEntryVO.getQ4FYS());
		annualTargetEntry.setQ4FYT(annualTargetEntryVO.getQ4FYT());
		annualTargetEntry.setFY(annualTargetEntryVO.getFY());
		return AnnualTargetEntryConverter
				.convertAnnualTargetEntryToAnnualTargetEntryVO(annualTargetEntryRepository.save(annualTargetEntry));
	}

	@Override
	@Transactional
	public void saveExcelDataOfAnnualTargetEntry(MultipartFile file, String financialYear) {
		List<AnnualTargetEntryVO> annualTargetEntryVOs = ExcelHelper.convertExceltoListOfAnnualTargetEntry(file,
				financialYear);
		List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();
		annualTargetEntryVOs.stream().forEach(annualTargetEntryVO -> annualTargetEntries
				.add(AnnualTargetEntryConverter.convertAnnualTargetEntryVOToAnnualTargetEntry(annualTargetEntryVO)));
		annualTargetEntryRepository.saveAll(annualTargetEntries);
	}

}
