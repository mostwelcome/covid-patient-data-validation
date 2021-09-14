package com.psl.validate.CovidValidate.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.validate.CovidValidate.Repository.ValidatedDataRepository;
import com.psl.validate.CovidValidate.constant.ApplicationConstants;
import com.psl.validate.CovidValidate.controller.ValidationController;
import com.psl.validate.CovidValidate.model.ValidatedData;

@Service
public class ValidatorService {

	@Autowired
	ValidationController validationController;

	@Autowired
	ValidatedDataRepository validatedDataRepository;

	private List<String> sex = new ArrayList<>();

	private List<String> status = new ArrayList<>();

	private List<String> hospitalizationStatus = new ArrayList<>();

	private List<String> ward = new ArrayList<>();

	private List<String> sampleResult = new ArrayList<>();

	private List<String> testingLabs = new ArrayList<>();

	private Map<Integer, List<String>> validatedMapping = new HashMap<>();

	public List<String> getSex() {
		return sex;
	}

	public List<String> getStatus() {
		return status;
	}

	public List<String> getHospitalizationStatus() {
		return hospitalizationStatus;
	}

	public List<String> getWardList() {
		return ward;
	}

	public List<String> getSampleResult() {
		return sampleResult;
	}

	public List<String> getTestingLabs() {
		return testingLabs;
	}

	public void populateValidateData() {

		String fileName = "ValidData.xlsx";
		ClassLoader classLoader = new ValidatorService().getClass().getClassLoader();

		File file = new File(classLoader.getResource(fileName).getFile());

		try {
			FileInputStream fis = new FileInputStream(file);

			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sheet = wb.getSheetAt(0);

			int numRows = sheet.getLastRowNum() + 1;
			int numCols = sheet.getRow(0).getLastCellNum();

			String[][] excelData = new String[numRows][numCols];

			for (int i = 0; i < numRows; i++) {
				XSSFRow row = sheet.getRow(i);
				if (row == null)
					continue;
				for (int j = 0; j < numCols; j++) {
					XSSFCell cell = row.getCell(j);
					if (cell == null)
						continue;
					String value = cell.toString();
					excelData[i][j] = value;
				}
			}

			for (int i = 1; i < numRows; i++)
				if (excelData[i][0] != null)
					sex.add(excelData[i][0]);

			for (int i = 1; i < numRows; i++)
				if (excelData[i][1] != null)
					status.add(excelData[i][1]);

			for (int i = 1; i < numRows; i++)
				if (excelData[i][2] != null)
					hospitalizationStatus.add(excelData[i][2]);

			for (int i = 1; i < numRows; i++)
				if (excelData[i][3] != null)
					ward.add(excelData[i][3]);

			for (int i = 1; i < numRows; i++)
				if (excelData[i][4] != null)
					sampleResult.add(excelData[i][4]);

			for (int i = 1; i < numRows; i++)
				if (excelData[i][5] != null)
					testingLabs.add(excelData[i][5]);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		validatedMapping.put(ApplicationConstants.sexColumn, sex);
		validatedMapping.put(ApplicationConstants.wardColumn, ward);
		validatedMapping.put(ApplicationConstants.sampleColumn, sampleResult);
		validatedMapping.put(ApplicationConstants.hospitalizationStatusColumn, hospitalizationStatus);
		validatedMapping.put(ApplicationConstants.labsColumn, testingLabs);
		validatedMapping.put(ApplicationConstants.statusColumn, status);

	}

	public Map<Integer, Map<String, String>> validateData(XSSFSheet worksheet) {

		ValidatedData validatedData = new ValidatedData();

		Map<Integer, Map<String, String>> outer = new HashMap<>();

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = worksheet.getRow(i);

			Map<String, String> errorMap = new HashMap<>();

			for (int j = 0; j < worksheet.getRow(0).getLastCellNum(); j++) {

				Cell c = row.getCell(j);

				// age validation : not null, numeric value and age should be >0 and <=100
				if (j == ApplicationConstants.ageColumn) {
					if (c == null || (c.getCellType() != XSSFCell.CELL_TYPE_NUMERIC
							|| (Float.parseFloat(c.toString()) < Float.valueOf(0)
									|| Float.parseFloat(c.toString()) > Float.valueOf(100))))
						errorMap.put(worksheet.getRow(0).getCell(j).toString(), ApplicationConstants.errormsg + c);
				}
				// contact number validation: allows not null, numeric and 10 digit number.
				if (j == ApplicationConstants.contactColumn) {
					if (c == null || (c.getCellType() != XSSFCell.CELL_TYPE_NUMERIC)) {
						errorMap.put(worksheet.getRow(0).getCell(j).toString(), ApplicationConstants.errormsg + c);
					} else if (c != null && (c.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
							&& String.valueOf(c.getNumericCellValue()).length() < 10)
						errorMap.put(worksheet.getRow(0).getCell(j).toString(), ApplicationConstants.errormsg + c);
				}

				// district checking (cannot be null and can only be pune)
				if (j == ApplicationConstants.districtColumn) {
					if (c == null || (!c.toString().equalsIgnoreCase(ApplicationConstants.district)))
						errorMap.put(worksheet.getRow(ApplicationConstants.header).getCell(j).toString(),
								ApplicationConstants.errormsg + c + " . Can only be " + ApplicationConstants.district);
				}

				if (j == ApplicationConstants.dateOfReceiptofInfo || j == ApplicationConstants.dateOfSampleCollection) {

					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdf3 = new SimpleDateFormat("dd.MM.yyyy");
					
					Boolean format1 = getValidateDate(c.toString(), sdf1);
					Boolean format2 = getValidateDate(c.toString(), sdf2);
					Boolean format3 = getValidateDate(c.toString(), sdf3);

					if (format1 == false && format2 == false && format3 == false) {
						errorMap.put(worksheet.getRow(ApplicationConstants.header).getCell(j).toString(),
								ApplicationConstants.errormsg + c.toString());
					}

				}

				// Those are already stored in list
				if (validatedMapping.containsKey(j)) {
					if (c == null || (!validatedMapping.get(j).contains(c.toString()))) {
						errorMap.put(worksheet.getRow(ApplicationConstants.header).getCell(j).toString(),
								ApplicationConstants.errormsg + c);
					}
				}
			}

			if (errorMap.size() != 0) {
				outer.put((int) Float.parseFloat(row.getCell(0).toString()), errorMap);
			} else {

				validatedData.setSerialNo((int) Float.parseFloat(row.getCell(0).toString()));
				validatedData.setDistrict(row.getCell(1).toString());
				validatedData.setAge((int) Float.parseFloat(row.getCell(ApplicationConstants.ageColumn).toString()));
				validatedData
						.setContactNo((long) row.getCell(ApplicationConstants.contactColumn).getNumericCellValue());
				validatedData.setWard(row.getCell(ApplicationConstants.wardColumn).toString());
				validatedData.setHospitalizationStatus(
						row.getCell(ApplicationConstants.hospitalizationStatusColumn).toString());
				validatedData.setCurrentStatus(row.getCell(ApplicationConstants.statusColumn).toString());
				validatedData.setSex(row.getCell(ApplicationConstants.sexColumn).toString());

				validatedDataRepository.save(validatedData);
			}

		}
		return outer;
	}

	private Boolean getValidateDate(String date, SimpleDateFormat formatter) {

		Boolean b = true;
		formatter.setLenient(false);
		try {
			Date d = formatter.parse(date);

		} catch (ParseException e) {
			b = false;
		}
		return b;

	}

}
