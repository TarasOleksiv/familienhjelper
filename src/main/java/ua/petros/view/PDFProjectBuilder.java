package ua.petros.view;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPCell;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import ua.petros.model.Beneficiary;
import ua.petros.model.Project;
import ua.petros.model.Transaction;
import ua.petros.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class builds a PDF document using Apache POI library.
 * @author www.codejava.net
 *
 */

public class PDFProjectBuilder extends AbstractPdfView {
	//private Table table;
	private PdfPTable table;
	private Paragraph paragraph;
	private BigDecimal totalAmount;
	private BigDecimal totalDonation;
	private BigDecimal totalExpense;
	private BigDecimal beneficiaryDonation;
	private BigDecimal beneficiaryExpense;
	private String firstName;
	private String lastName;
	private String fieldContactFirstName;
	private String fieldContactLastName;
	private String beneficiaryName;

	public static final String FONT = "fonts/arial.ttf";

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
									Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"projects.pdf\"");

		List<Project> listProjects = (List<Project>) model.get("listProjects");
		Date startDate = (Date) model.get("startDate");
		Date endDate = (Date) model.get("endDate");
		String isWholePeriod = (String) model.get("isWholePeriod");
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String strStartDate = dateFormat.format(startDate);
		String strEndDate = dateFormat.format(endDate);
		User fieldContact = (User) model.get("fieldContact");
		fieldContactFirstName = (fieldContact.getFirstName() == null ? "" : fieldContact.getFirstName());
		fieldContactLastName = (fieldContact.getLastName() == null ? "" : fieldContact.getLastName());
		Beneficiary beneficiarySelected = (Beneficiary) model.get("beneficiary");
		String beneficiarySelectedName = (beneficiarySelected.getName() == null ? "" : beneficiarySelected.getName());
		String isAllFieldContacts = (String) model.get("isAllFieldContacts");
		String isAllBeneficiaries = (String) model.get("isAllBeneficiaries");

		BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font f1 = new Font(bf, 10);
		Font f2 = new Font(bf,10,Font.BOLD);
		beneficiaryDonation = BigDecimal.valueOf(0);
		beneficiaryExpense = BigDecimal.valueOf(0);

		if (listProjects.size() == 0){
			paragraph = new Paragraph("*********************************************************************************************", f2);
			paragraph.add(new Paragraph(" "));
			paragraph.add(new Paragraph("No data found", f1));
			paragraph.add(new Paragraph(" "));
			paragraph.add(new Paragraph("*********************************************************************************************", f2));
			document.add(paragraph);
			return;
		}
		
		for (Project project : listProjects) {
			document.add(new Paragraph("*********************************************************************************************", f2));
			String projectLine = "Project: " + project.getName();
			projectLine = "true".equals(isWholePeriod) ? projectLine + "        Period: since start" : projectLine + "       " + "From: " + strStartDate + "       To: " + strEndDate;
			document.add(new Paragraph(projectLine,f2));
			document.add(new Paragraph("Status: " + project.getStatus().getName(),f1));

			if (project.getBeneficiary() != null){
				beneficiaryName = (project.getBeneficiary().getName() == null ? "" : project.getBeneficiary().getName());
			} else {
				beneficiaryName = "";
			}

			document.add(new Paragraph("Beneficiary: " + beneficiaryName,f1));

			if (project.getFuUser() != null){
				firstName = (project.getFuUser().getFirstName() == null ? "" : project.getFuUser().getFirstName());
				lastName = (project.getFuUser().getLastName() == null ? "" : project.getFuUser().getLastName());
			} else {
				firstName = "";
				lastName = "";
			}
			firstName = updateFirstName(firstName,lastName);
			String contactLine = "FU: " + firstName + " " + lastName;

			if (project.getFieldContactUser() != null){
				firstName = (project.getFieldContactUser().getFirstName() == null ? "" : project.getFieldContactUser().getFirstName());
				lastName = (project.getFieldContactUser().getLastName() == null ? "" : project.getFieldContactUser().getLastName());
			} else {
				firstName = "";
				lastName = "";
			}
			firstName = updateFirstName(firstName,lastName);
			contactLine += "      Field contact: " + firstName + " " + lastName;

			document.add(new Paragraph(contactLine,f1));
			table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setWidths(new float[]{2,4,2,1,8});
			table.getDefaultCell().setBorder(0);
			totalAmount = BigDecimal.valueOf(0);
			totalDonation = BigDecimal.valueOf(0);
			totalExpense = BigDecimal.valueOf(0);
			for (Transaction transaction : project.getSortedTransactions()){
				if (("true".equals(isWholePeriod)) ||
						(transaction.getTradingDate().compareTo(startDate)>=0 && transaction.getTradingDate().compareTo(endDate)<=0)) {
					table.addCell(new PdfPCell(new Phrase(new SimpleDateFormat("dd.MM.yyyy").format(transaction.getTradingDate()),f1)));
					if (transaction.getIsIncome()) {
						table.addCell(new PdfPCell(new Phrase(transaction.getMember().getName(),f1)));
					} else {
						table.addCell(new PdfPCell(new Phrase(transaction.getBeneficiary().getName(),f1)));
					}
					if (transaction.getIsIncome()) {
						table.addCell(new PdfPCell(new Phrase(transaction.getAmountNOK().toString(),f1)));
						totalAmount = totalAmount.add(transaction.getAmountNOK());
						totalDonation = totalDonation.add(transaction.getAmountNOK());
						beneficiaryDonation = beneficiaryDonation.add(transaction.getAmountNOK());
					} else {
						table.addCell(new PdfPCell(new Phrase(BigDecimal.valueOf(-1).multiply(transaction.getAmountNOK()).toString(),f1)));
						totalAmount = totalAmount.subtract(transaction.getAmountNOK());
						totalExpense = totalExpense.add(transaction.getAmountNOK());
						beneficiaryExpense = beneficiaryExpense.add(transaction.getAmountNOK());
					}
					table.addCell(new PdfPCell(new Phrase("NOK",f1)));
					table.addCell(new PdfPCell(new Phrase(transaction.getDescription(),f1)));
				}
			}

			document.add(new Paragraph(" ",f1));
			document.add(table);

			totalExpense = BigDecimal.valueOf(-1).multiply(totalExpense);
			document.add(new Paragraph("Donation: " + totalDonation.toString() + " NOK" + "     " + "Expense: " + totalExpense.toString() + " NOK",f1));
			document.add(new Paragraph("_________________________________________________________________",f1));
			document.add(new Paragraph("Balance for the period:                                                 " + totalAmount.toString() + " NOK",f1));
			document.add(new Paragraph("==============================================================",f1));
			document.add(new Paragraph(" ",f1));
		}

		if ("false".equals(isAllBeneficiaries)) {
			beneficiaryExpense = BigDecimal.valueOf(-1).multiply(beneficiaryExpense);
			document.add(new Paragraph("*********************************************************************************************", f2));
			document.add(new Paragraph("Totals for the beneficiary: " + beneficiarySelectedName,f2));
			document.add(new Paragraph("Total Donation: " + beneficiaryDonation.toString() + " NOK" + "     " + "Total Expense: " + beneficiaryExpense.toString() + " NOK",f1));
			document.add(new Paragraph("*********************************************************************************************", f2));
		}
	}

	private String updateFirstName(String firstName, String lastName){
		return
				"".equals(firstName) && "".equals(lastName) ? "N/A" : firstName;
	}

}