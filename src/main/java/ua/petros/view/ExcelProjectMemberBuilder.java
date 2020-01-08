package ua.petros.view;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import ua.petros.model.Project;
import ua.petros.model.ProjectMember;

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

public class ExcelProjectMemberBuilder extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  Workbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"project_donors.xlsx\"");

		// get data model which is passed by the Spring container
		List<ProjectMember> listProjectMembers = (List<ProjectMember>) model.get("listProjectMembers");
		
		// create a new Excel sheet
		Sheet sheet = workbook.createSheet("Project Donors");
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

		// "Project", "Donor", "Pledge", "Start Pledge", "Stop Pledge"

		header.createCell(0).setCellValue("Project");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Donor");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("Pledge");
		header.getCell(2).setCellStyle(style);
		
		header.createCell(3).setCellValue("Start Pledge");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("Stop Pledge");
		header.getCell(4).setCellStyle(style);

		// create data rows
		int rowCount = 1;
		
		for (ProjectMember projectMember : listProjectMembers) {
			Row aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(projectMember.getProject().getName());
			if (projectMember.getMember() != null){
				aRow.createCell(1).setCellValue(projectMember.getMember().getName());
			}
			if (projectMember.getPledge() != null){
				aRow.createCell(2).setCellValue(projectMember.getPledge().toString());
			}
			if (projectMember.getStartPledge() != null){
				aRow.createCell(3).setCellValue(new SimpleDateFormat("dd.MM.yyyy").format(projectMember.getStartPledge()));
			}
			if (projectMember.getStopPledge() != null){
				aRow.createCell(4).setCellValue(new SimpleDateFormat("dd.MM.yyyy").format(projectMember.getStopPledge()));
			}
		}
	}

}