package com.psl.validate.CovidValidate.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.validate.CovidValidate.Service.ValidatorService;
import com.psl.validate.CovidValidate.constant.ApplicationConstants;
import com.psl.validate.CovidValidate.model.ValidatedData;

@RestController
@RequestMapping("validate")
public class ValidationController {

	@Autowired
	ValidatorService validatorService;
	
/*	@Autowired
	ValidatedData validatedData;
	
*/

	@GetMapping(value = "/working")
	public List<String> getWorking() {
		return validatorService.getSex();
	}

	@PostMapping("/import")
	public Map<Integer, Map<String, String> > mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
		
		Map<String, String> inner=new HashMap<String, String>();
		
		Map<Integer, Map<String, String> > outer= new HashMap<>();

		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		System.out.println(worksheet.getPhysicalNumberOfRows());

		
		outer=validatorService.validateData(worksheet);
	
		return outer;

	}

}
