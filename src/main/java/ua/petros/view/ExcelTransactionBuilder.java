package ua.petros.view;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import ua.petros.model.Beneficiary;
import ua.petros.model.Transaction;

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

public class ExcelTransactionBuilder extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  Workbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"transactions.xlsx\"");

		// get data model which is passed by the Spring container
		List<Transaction> listTransactions = (List<Transaction>) model.get("listTransactions");
		
		// create a new Excel sheet
		Sheet sheet = workbook.createSheet("Transactions");
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

		// "Project", "Date", "Amount",
		//				"Currency", "Amount NOK", "From Donor", "To Beneficiary", "Transaction Type"

		header.createCell(0).setCellValue("Project");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Date");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Amount");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("Currency");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("Amount NOK");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("From Donor");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("To Beneficiary");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("Transaction Type");
		header.getCell(7).setCellStyle(style);
		// create data rows
		int rowCount = 1;
		
		for (Transaction transaction : listTransactions) {
			Row aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(transaction.getProject().getName());
			if (transaction.getTradingDate() != null){
				aRow.createCell(1).setCellValue(new SimpleDateFormat("dd.MM.yyyy").format(transaction.getTradingDate()));
			}
			if (transaction.getAmount() != null){
				aRow.createCell(2).setCellValue(transaction.getAmount().toString());
			}
			if (transaction.getCurrency() != null){
				aRow.createCell(3).setCellValue(transaction.getCurrency().getName());
			}
			if (transaction.getAmountNOK() != null){
				aRow.createCell(4).setCellValue(transaction.getAmountNOK().toString());
			}
			if (transaction.getMember() != null){
				aRow.createCell(5).setCellValue(transaction.getMember().getName());
			}
			if (transaction.getBeneficiary() != null){
				aRow.createCell(6).setCellValue(transaction.getBeneficiary().getName());
			}
			if (transaction.getTransactionType() != null){
				aRow.createCell(7).setCellValue(transaction.getTransactionType().getName());
			}
		}
	}

}