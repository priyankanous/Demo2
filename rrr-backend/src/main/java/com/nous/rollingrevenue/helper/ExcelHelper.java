package com.nous.rollingrevenue.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.nous.rollingrevenue.convertor.AccountConverter;
import com.nous.rollingrevenue.exception.ExcelParserException;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.repository.AccountRepository;
import com.nous.rollingrevenue.service.FinancialYearService;
import com.nous.rollingrevenue.vo.AccountVO;
import com.nous.rollingrevenue.vo.AnnualTargetEntryVO;
import com.nous.rollingrevenue.vo.BDMVO;
import com.nous.rollingrevenue.vo.BusinessTypeVO;
import com.nous.rollingrevenue.vo.BusinessUnitVO;
import com.nous.rollingrevenue.vo.CocPracticeVO;
import com.nous.rollingrevenue.vo.FinancialYearVO;
import com.nous.rollingrevenue.vo.LocationVO;
import com.nous.rollingrevenue.vo.RegionVO;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitHeadVO;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitVO;
import com.nous.rollingrevenue.vo.WorkOrderVO;

@Component
public class ExcelHelper {

	@Autowired
	private FinancialYearService financialYearService;

	@Autowired
	private AccountRepository accountRepository;

	public static final String EXCEL_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	// check the file is excel type or not
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if (EXCEL_CONTENT_TYPE.equals(contentType)) {
			return true;
		}
		return false;
	}

	// convert excel to list of AnnualTargetEntries
	public List<AnnualTargetEntryVO> convertExceltoListOfAnnualTargetEntry(MultipartFile file, String financialYear) {
		FinancialYearVO financialYearByName = financialYearService.getFinancialYearByName(financialYear);
		List<AnnualTargetEntryVO> annualTargetEntryVOs = new ArrayList<>();
		int rowNumber = 0;

		try (XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream())) {
			XSSFSheet sheet = workBook.getSheetAt(0);
			Iterator<Row> rowIterater = sheet.iterator();
			while (rowIterater.hasNext()) {
				Row row = rowIterater.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				int cellId = 0;
				AnnualTargetEntryVO annualTargetEntryVO = new AnnualTargetEntryVO();
				Iterator<Cell> cellIterator = row.iterator();
				while (cellIterator.hasNext()) {
					annualTargetEntryVO.setFinancialYear(financialYearByName);
					Cell cell = cellIterator.next();
					switch (cellId) {

					case 0:
						BusinessUnitVO businessUnit = new BusinessUnitVO();
						String businessUnitName = cell.getStringCellValue().isBlank() ? null
								: cell.getStringCellValue();
						businessUnit.setBusinessUnitName(businessUnitName);
						annualTargetEntryVO.setBusinessUnit(businessUnit);
						break;
					case 1:
						StrategicBusinessUnitVO sbu = new StrategicBusinessUnitVO();
						String sbuName = cell.getStringCellValue().isBlank() ? null : cell.getStringCellValue();
						sbu.setSbuName(sbuName);
						annualTargetEntryVO.setStartegicBusinessUnit(sbu);
						break;
					case 2:
						StrategicBusinessUnitHeadVO sbuHead = new StrategicBusinessUnitHeadVO();
						String sbuHeadName = cell.getStringCellValue().isBlank() ? null : cell.getStringCellValue();
						sbuHead.setSbuHeadName(sbuHeadName);
						annualTargetEntryVO.setStrategicBusinessUnitHead(sbuHead);
						break;
					case 3:
						LocationVO location = new LocationVO();
						String locationName = cell.getStringCellValue().isBlank() ? null : cell.getStringCellValue();
						location.setLocationName(locationName);
						annualTargetEntryVO.setLocation(location);
						break;
					case 4:
						RegionVO region = new RegionVO();
						String regionName = cell.getStringCellValue().isBlank() ? null : cell.getStringCellValue();
						region.setRegionName(regionName);
						annualTargetEntryVO.setRegion(region);
						break;
					case 5:
						AccountVO account = new AccountVO();
						String accountname = cell.getStringCellValue().isBlank() ? null : cell.getStringCellValue();
						account.setAccountName(accountname);
						annualTargetEntryVO.setAccount(account);
						break;
					case 6:
						BusinessTypeVO businessType = new BusinessTypeVO();
						String businessTypeName = cell.getStringCellValue().isBlank() ? null
								: cell.getStringCellValue();
						businessType.setBusinessTypeName(businessTypeName);
						annualTargetEntryVO.setBusinessType(businessType);
						break;
					case 7:
						CocPracticeVO cocPractice = new CocPracticeVO();
						String cocPracticeName = cell.getStringCellValue().isBlank() ? null : cell.getStringCellValue();
						cocPractice.setCocPracticeName(cocPracticeName);
						annualTargetEntryVO.setCocPractice(cocPractice);
						break;
					case 8:
						BDMVO bdm = new BDMVO();
						String bdmName = cell.getStringCellValue().isBlank() ? null : cell.getStringCellValue();
						bdm.setBdmName(bdmName);
						annualTargetEntryVO.setBusinessDevelopmentManager(bdm);
						break;
					case 9:
						annualTargetEntryVO.setQ1FYB(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 10:
						annualTargetEntryVO.setQ1FYS(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 11:
						annualTargetEntryVO.setQ1FYT(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 12:
						annualTargetEntryVO.setQ2FYB(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 13:
						annualTargetEntryVO.setQ2FYS(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 14:
						annualTargetEntryVO.setQ2FYT(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 15:
						annualTargetEntryVO.setQ3FYB(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 16:
						annualTargetEntryVO.setQ3FYS(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 17:
						annualTargetEntryVO.setQ3FYT(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 18:
						annualTargetEntryVO.setQ4FYB(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 19:
						annualTargetEntryVO.setQ4FYS(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 20:
						annualTargetEntryVO.setQ4FYT(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					case 21:
						annualTargetEntryVO.setFY(BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger());
						break;
					default:
						break;
					}
					cellId++;
				}
				annualTargetEntryVOs.add(annualTargetEntryVO);
			}
		} catch (Exception e) {
			throw new ExcelParserException(e.getLocalizedMessage());
		}
		return annualTargetEntryVOs;
	}

	// convert excel to list of WorkOrderEntries
	public List<WorkOrderVO> convertExceltoListOfWorkOrder(MultipartFile file) {
		List<WorkOrderVO> excelWorkOrderVOs = new ArrayList<>();
		List<String> unMatchedAccountNames = new ArrayList<>();
		List<Account> accounts = accountRepository.findAll();
		int rowNumber = 0;

		try (XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream())) {
			XSSFSheet sheet = workBook.getSheetAt(0);
			Iterator<Row> rowIterater = sheet.iterator();
			while (rowIterater.hasNext()) {
				Row row = rowIterater.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				int cellId = 0;
				WorkOrderVO workOrderVO = new WorkOrderVO();
				Iterator<Cell> cellIterator = row.iterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cellId) {

					case 0:
						workOrderVO.setWorkOrderNumber(
								(cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;

					case 5:
						workOrderVO.setWorkOrderEndDate(cell.getLocalDateTimeCellValue() == null ? null
								: cell.getLocalDateTimeCellValue().toLocalDate());
						break;
					case 6:
						workOrderVO.setWorkOrderStatus(
								(cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;

					case 7:
						String accountName = cell.getStringCellValue().isBlank() ? null : cell.getStringCellValue();
						if (!accounts.isEmpty()) {
							Optional<Account> accountNameMatch = accounts.stream()
									.filter(account -> account.getAccountName().replaceAll(" ", "")
											.equalsIgnoreCase(accountName.replaceAll(" ", "")))
									.findFirst();
							if (accountNameMatch.isPresent()) {
								workOrderVO
										.setAccount(AccountConverter.convertAccountToAccountVO(accountNameMatch.get()));
							} else {
								unMatchedAccountNames.add(accountName);
							}
						}
						break;

					default:
						break;
					}
					cellId++;
				}
				excelWorkOrderVOs.add(workOrderVO);
			}
		} catch (Exception e) {
			throw new ExcelParserException(e.getLocalizedMessage());
		}
		if (unMatchedAccountNames.size() > 0) {
			throw new RecordNotFoundException(unMatchedAccountNames.toString());
		}
		return excelWorkOrderVOs;
	}

}
