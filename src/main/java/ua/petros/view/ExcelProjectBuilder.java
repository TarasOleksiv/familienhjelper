package ua.petros.view;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import ua.petros.model.Beneficiary;
import ua.petros.model.Project;

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

public class ExcelProjectBuilder extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  Workbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"projects.xlsx\"");

		// get data model which is passed by the Spring container
		List<Project> listProjects = (List<Project>) model.get("listProjects");
		
		// create a new Excel sheet
		Sheet sheet = workbook.createSheet("Projects");
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

		// "Name", "Balance NOK", "Start Date",
		//				"Stop Date", "Status", "FU", "Field Contact", "Description", "Feedback"

		header.createCell(0).setCellValue("Name");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Balance NOK");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Start Date");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("Stop Date");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("Status");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("FU");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("Field Contact");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("Description");
		header.getCell(7).setCellStyle(style);

		header.createCell(8).setCellValue("Feedback");
		header.getCell(8).setCellStyle(style);

		// create data rows
		int rowCount = 1;
		
		for (Project project : listProjects) {
			Row aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(project.getName());
			if (project.getBalance() != null){
				aRow.createCell(1).setCellValue(project.getBalance().toString());
			}
			if (project.getStartDate() != null){
				aRow.createCell(2).setCellValue(new SimpleDateFormat("dd.MM.yyyy").format(project.getStartDate()));
			}
			if (project.getStopDate() != null){
				aRow.createCell(3).setCellValue(new SimpleDateFormat("dd.MM.yyyy").format(project.getStopDate()));
			}
			if (project.getStatus() != null){
				aRow.createCell(4).setCellValue(project.getStatus().getName());
			}
			if (project.getFuUser() != null){
				String firstName = (project.getFuUser().getFirstName() == null ? "N/A" : project.getFuUser().getFirstName());
				String lastName = (project.getFuUser().getLastName() == null ? "N/A" : project.getFuUser().getLastName());
				aRow.createCell(5).setCellValue(firstName + " " + lastName);
			}
			if (project.getFieldContactUser() != null){
				String firstName = (project.getFieldContactUser().getFirstName() == null ? "N/A" : project.getFieldContactUser().getFirstName());
				String lastName = (project.getFieldContactUser().getLastName() == null ? "N/A" : project.getFieldContactUser().getLastName());
				aRow.createCell(6).setCellValue(firstName + " " + lastName);
			}
			aRow.createCell(7).setCellValue(project.getDescription());
			aRow.createCell(8).setCellValue(project.getFeedback());
		}
	}

}