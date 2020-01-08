package ua.petros.view;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
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

// https://stackoverflow.com/questions/37983946/abstractexcelview-is-deprecated-in-spring-based-application

public class ExcelUserBuilder extends AbstractXlsxView {

	// private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  Workbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"users.xlsx\"");

		// get data model which is passed by the Spring container
		List<User> listUsers = (List<User>) model.get("listUsers");
		
		// create a new Excel sheet
		Sheet sheet = workbook.createSheet("Users");
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

		// "Username", "First Name", "Last Name", "Email",
		//				"Mobile1", "Mobile2", "Address", "Account", "Bank", "Role"

		header.createCell(0).setCellValue("Username");
		header.getCell(0).setCellStyle(style);
		
		header.createCell(1).setCellValue("First Name");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Last Name");
		header.getCell(2).setCellStyle(style);
		
		header.createCell(3).setCellValue("Email");
		header.getCell(3).setCellStyle(style);
		
		header.createCell(4).setCellValue("Mobile1");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("Mobile2");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("Address");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("Account");
		header.getCell(7).setCellStyle(style);

		header.createCell(8).setCellValue("Bank");
		header.getCell(8).setCellStyle(style);

		header.createCell(9).setCellValue("Role");
		header.getCell(9).setCellStyle(style);
		// create data rows
		int rowCount = 1;
		
		for (User user : listUsers) {
			Row aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(user.getUsername());
			aRow.createCell(1).setCellValue(user.getFirstName());
			aRow.createCell(2).setCellValue(user.getLastName());
			aRow.createCell(3).setCellValue(user.getEmail());
			aRow.createCell(4).setCellValue(user.getMobile1());
			aRow.createCell(5).setCellValue(user.getMobile2());
			aRow.createCell(6).setCellValue(user.getAddress());
			aRow.createCell(7).setCellValue(user.getAccount());
			aRow.createCell(8).setCellValue(user.getBank());
			aRow.createCell(9).setCellValue(user.getRoles().iterator().next().getName());
		}
	}

}