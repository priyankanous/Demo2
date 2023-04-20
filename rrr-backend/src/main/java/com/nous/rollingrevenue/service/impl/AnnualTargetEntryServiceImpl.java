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
import org.springframework.web.multipart.MultipartFile;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.AnnualTargetEntryConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.helper.ExcelHelper;
import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.model.BusinessType;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.Location;
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.model.StrategicBusinessUnit;
import com.nous.rollingrevenue.model.StrategicBusinessUnitHead;
import com.nous.rollingrevenue.repository.AccountRepository;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.repository.BusinessDevelopmentManagerRepository;
import com.nous.rollingrevenue.repository.BusinessTypeRepository;
import com.nous.rollingrevenue.repository.BusinessUnitRepository;
import com.nous.rollingrevenue.repository.CocPracticeRepository;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.LocationRepository;
import com.nous.rollingrevenue.repository.RegionRepository;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitHeadRepository;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitRepository;
import com.nous.rollingrevenue.service.AnnualTargetEntryService;
import com.nous.rollingrevenue.vo.AnnualTargetEntryVO;

@Service
@Transactional(readOnly = true)
public class AnnualTargetEntryServiceImpl implements AnnualTargetEntryService {

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;
	
	@Autowired
	private FinancialYearRepository financialYearRepository;
	
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	
	@Autowired
	private StrategicBusinessUnitRepository sbuRepository;
	
	@Autowired
	private StrategicBusinessUnitHeadRepository sbuHeadRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BusinessTypeRepository businessTypeRepository;
	
	@Autowired
	private CocPracticeRepository cocPracticeRepository;
	
	@Autowired
	private BusinessDevelopmentManagerRepository bdmRepository;
	
	@Autowired
	private ExcelHelper excelHelper;

	@Override
	public List<AnnualTargetEntryVO> getAllAnnualTargetEntry() {
		List<AnnualTargetEntryVO> annualTargetEntryVOs = new ArrayList<>();
		annualTargetEntryRepository.findAll().stream().forEach(annualTargetEntry -> annualTargetEntryVOs
				.add(AnnualTargetEntryConverter.convertAnnualTargetEntryToAnnualTargetEntryVO(annualTargetEntry)));
		return annualTargetEntryVOs;
	}

	@Override
	@Transactional
	public void saveAnnualTargetEntry(AnnualTargetEntryVO annualTargetEntryVO) {
		annualTargetEntryRepository
				.save(AnnualTargetEntryConverter.convertAnnualTargetEntryVOToAnnualTargetEntry(annualTargetEntryVO));
	}

	@Override
	@Transactional
	public void deleteAnnualTargetEntryById(Long annualTargetEntryId) {
		annualTargetEntryRepository.findById(annualTargetEntryId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryId));
		annualTargetEntryRepository.deleteById(annualTargetEntryId);
	}

	@Override
	public AnnualTargetEntryVO getAnnualTargetEntryById(Long annualTargetEntryId) {
		AnnualTargetEntry annualTargetEntry = annualTargetEntryRepository.findById(annualTargetEntryId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryId));
		return AnnualTargetEntryConverter.convertAnnualTargetEntryToAnnualTargetEntryVO(annualTargetEntry);
	}

	@Override
	@Transactional
	public void updateAnnualTargetEntry(Long annualTargetEntryId,
			AnnualTargetEntryVO annualTargetEntryVO) {
		AnnualTargetEntry annualTargetEntry = annualTargetEntryRepository.findById(annualTargetEntryId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + annualTargetEntryId));
		FinancialYear financialYear = financialYearRepository
				.findById(annualTargetEntryVO.getFinancialYear().getFinancialYearId()).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "FinancialYear"));
		annualTargetEntry.setFinancialYear(financialYear);
		BusinessUnit businessUnit = businessUnitRepository.findById(annualTargetEntryVO.getBusinessUnit().getBusinessUnitId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "BusinessUnit"));
		annualTargetEntry.setBusinessUnit(businessUnit);
		StrategicBusinessUnit sbu = sbuRepository.findById(annualTargetEntryVO.getStartegicBusinessUnit().getSbuId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "StrategicBusinessUnit"));
		annualTargetEntry.setStartegicBusinessUnit(sbu);
		StrategicBusinessUnitHead sbuHead = sbuHeadRepository.findById(annualTargetEntryVO.getStrategicBusinessUnitHead().getSbuHeadId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "StrategicBusinessUnitHead"));
		annualTargetEntry.setStrategicBusinessUnitHead(sbuHead);
		Location location = locationRepository.findById(annualTargetEntryVO.getLocation().getLocationId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Location"));
		annualTargetEntry.setLocation(location);
		Region region = regionRepository.findById(annualTargetEntryVO.getRegion().getRegionId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Region"));
		annualTargetEntry.setRegion(region);
		Account account = accountRepository.findById(annualTargetEntryVO.getAccount().getAccountId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Account"));
		annualTargetEntry.setAccount(account);
		BusinessType businessType = businessTypeRepository.findById(annualTargetEntryVO.getBusinessType().getBusinessTypeId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "BusinessType"));
		annualTargetEntry.setBusinessType(businessType);
		CocPractice cocPractice = cocPracticeRepository.findById(annualTargetEntryVO.getCocPractice().getCocPracticeId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "CocPractice"));
		annualTargetEntry.setCocPractice(cocPractice);
		BusinessDevelopmentManager bdm = bdmRepository.findById(annualTargetEntryVO.getBusinessDevelopmentManager().getBdmId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "BusinessDevelopmentManager"));
		annualTargetEntry.setBusinessDevelopmentManager(bdm);
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
		annualTargetEntryRepository.save(annualTargetEntry);
	}

	@Override
	@Transactional
	public void saveExcelDataOfAnnualTargetEntry(MultipartFile file, String financialYear) {
		List<AnnualTargetEntryVO> annualTargetEntryVOs = excelHelper.convertExceltoListOfAnnualTargetEntry(file,
				financialYear);
		List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();
		annualTargetEntryVOs.stream().forEach(annualTargetEntryVO -> annualTargetEntries
				.add(AnnualTargetEntryConverter.convertAnnualTargetEntryVOToAnnualTargetEntry(annualTargetEntryVO)));
		annualTargetEntryRepository.saveAll(annualTargetEntries);
	}

	@Override
	public List<AnnualTargetEntryVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<AnnualTargetEntryVO> annualTargetEntryVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<AnnualTargetEntry> pageResult = annualTargetEntryRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				annualTargetEntryVOs.add(AnnualTargetEntryConverter.convertAnnualTargetEntryToAnnualTargetEntryVO(e));
			});
			return annualTargetEntryVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long id) {
		AnnualTargetEntry annualTargetEntry = annualTargetEntryRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		annualTargetEntry.setActive(!annualTargetEntry.isActive());
		annualTargetEntryRepository.save(annualTargetEntry);
	}
}
