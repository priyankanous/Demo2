package com.nous.rollingrevenue.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nous.rollingrevenue.exception.ExcelParserException;
import com.nous.rollingrevenue.vo.AnnualTargetEntryVO;

public class ExcelHelper {

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
	public static List<AnnualTargetEntryVO> convertExceltoListOfAnnualTargetEntry(MultipartFile file,
			String financialYear) {
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
					annualTargetEntryVO.setFinancialYear(financialYear);
					Cell cell = cellIterator.next();
					switch (cellId) {

					case 0:
						annualTargetEntryVO.setBusinessUnit(
								(cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 1:
						annualTargetEntryVO.setStartegicBusinessUnit(
								(cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 2:
						annualTargetEntryVO.setStrategicBusinessUnitHead(
								(cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 3:
						annualTargetEntryVO
								.setLocation((cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 4:
						annualTargetEntryVO
								.setRegion((cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 5:
						annualTargetEntryVO
								.setAccount((cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 6:
						annualTargetEntryVO.setBusinessType(
								(cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 7:
						annualTargetEntryVO.setCocPractice(
								(cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 8:
						annualTargetEntryVO.setBusinessDevelopmentManager(
								(cell.getStringCellValue().isBlank()) ? null : cell.getStringCellValue());
						break;
					case 9:
						annualTargetEntryVO.setQ1FYB((long) cell.getNumericCellValue());
						break;
					case 10:
						annualTargetEntryVO.setQ1FYS((long) cell.getNumericCellValue());
						break;
					case 11:
						annualTargetEntryVO.setQ1FYT((long) cell.getNumericCellValue());
						break;
					case 12:
						annualTargetEntryVO.setQ2FYB((long) cell.getNumericCellValue());
						break;
					case 13:
						annualTargetEntryVO.setQ2FYS((long) cell.getNumericCellValue());
						break;
					case 14:
						annualTargetEntryVO.setQ2FYT((long) cell.getNumericCellValue());
						break;
					case 15:
						annualTargetEntryVO.setQ3FYB((long) cell.getNumericCellValue());
						break;
					case 16:
						annualTargetEntryVO.setQ3FYS((long) cell.getNumericCellValue());
						break;
					case 17:
						annualTargetEntryVO.setQ3FYT((long) cell.getNumericCellValue());
						break;
					case 18:
						annualTargetEntryVO.setQ4FYB((long) cell.getNumericCellValue());
						break;
					case 19:
						annualTargetEntryVO.setQ4FYS((long) cell.getNumericCellValue());
						break;
					case 20:
						annualTargetEntryVO.setQ4FYT((long) cell.getNumericCellValue());
						break;
					case 21:
						annualTargetEntryVO.setFY((long) cell.getNumericCellValue());
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

}
