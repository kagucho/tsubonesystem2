package tsuboneSystem.fpao;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import tsuboneSystem.dto.ExcelDto;

public interface ExcelFpao {
	
	HSSFWorkbook excelTemplate(ExcelDto dto);

}
