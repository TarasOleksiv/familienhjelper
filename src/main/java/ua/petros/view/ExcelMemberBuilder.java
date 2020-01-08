package ua.petros.view;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import ua.petros.model.Member;
import ua.petros.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */

public class ExcelMemberBuilder extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  Workbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"members.xlsx\"");

		// get data model which is passed by the Spring container
		List<Member> listMembers = (List<Member>) model.get("listMembers");
		
		// create a new Excel sheet
		Sheet sheet = workbook.createSheet("Members");
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

		// "Name", "Email",
		//				"Mobile", "City", "Account", "Bank", "DonorType"

		header.createCell(0).setCellValue("Name");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Email");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Mobile");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("City");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("Account");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("Bank");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("Donor Type");
		header.getCell(6).setCellStyle(style);
		// create data rows
		int rowCount = 1;
		
		for (Member member : listMembers) {
			Row aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(member.getName());
			aRow.createCell(1).setCellValue(member.getEmail());
			aRow.createCell(2).setCellValue(member.getMobile());
			aRow.createCell(3).setCellValue(member.getCity());
			aRow.createCell(4).setCellValue(member.getAccount());
			aRow.createCell(5).setCellValue(member.getBank());
			if (member.getDonorType() != null){
				aRow.createCell(6).setCellValue(member.getDonorType().getName());
			}
		}
	}

}