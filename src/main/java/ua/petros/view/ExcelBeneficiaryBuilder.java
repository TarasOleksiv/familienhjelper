package ua.petros.view;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import ua.petros.model.Beneficiary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */

public class ExcelBeneficiaryBuilder extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  Workbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"beneficiaries.xlsx\"");

		// get data model which is passed by the Spring container
		List<Beneficiary> listBeneficiaries = (List<Beneficiary>) model.get("listBeneficiaries");
		
		// create a new Excel sheet
		Sheet sheet = workbook.createSheet("Beneficiaries");
		sheet.setDefaultColumnWidth(20);
		
		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		
		// create header row
		Row header = sheet.createRow(0);

		// "Name", "Family", "Description",
		//				"Income", "Income Type", "Currency", "Born", "Field Contact"

		header.createCell(0).setCellValue("Name");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Family");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Description");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("Income");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("Income Type");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("Currency");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("Born");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("Field Contact");
		header.getCell(7).setCellStyle(style);
		// create data rows
		int rowCount = 1;
		
		for (Beneficiary beneficiary : listBeneficiaries) {
			Row aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(beneficiary.getName());
			aRow.createCell(1).setCellValue(beneficiary.getFamily());
			aRow.createCell(2).setCellValue(beneficiary.getDescription());
			if (beneficiary.getIncome() != null){
				aRow.createCell(3).setCellValue(beneficiary.getIncome().toString());
			}
			if (beneficiary.getIncomeType() != null){
				aRow.createCell(4).setCellValue(beneficiary.getIncomeType().getName());
			}
			if (beneficiary.getCurrency() != null){
				aRow.createCell(5).setCellValue(beneficiary.getCurrency().getName());
			}
			if (beneficiary.getDatefield() != null){
				aRow.createCell(6).setCellValue(new SimpleDateFormat("dd.MM.yyyy").format(beneficiary.getDatefield()));
			}
			if (beneficiary.getUser() != null){
				String firstName = (beneficiary.getUser().getFirstName() == null ? "N/A" : beneficiary.getUser().getFirstName());
				String lastName = (beneficiary.getUser().getLastName() == null ? "N/A" : beneficiary.getUser().getLastName());
				aRow.createCell(7).setCellValue(firstName + " " + lastName);
			}
		}
	}

}