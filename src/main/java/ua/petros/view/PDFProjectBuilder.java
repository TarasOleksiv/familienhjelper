package ua.petros.view;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
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
	private Table table;
	private Paragraph paragraph;
	private BigDecimal totalAmount;
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

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"projects.pdf\"");

		// get data model which is passed by the Spring container
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
		String isAllFieldContacts = (String) model.get("isAllFieldContacts");

		BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font f1 = new Font(bf, 12);
		Font f2 = new Font(bf,12,Font.BOLD);

		if (listProjects.size() == 0){
			paragraph = new Paragraph("*********************************************************************************************", f2);
			paragraph.add(new Paragraph(" "));
			paragraph.add(new Paragraph("Field contact: " + fieldContactFirstName + " " + fieldContactLastName,f1));
			paragraph.add(new Paragraph(" "));
			paragraph.add(new Paragraph("No data found", f1));
			paragraph.add(new Paragraph(" "));
			paragraph.add(new Paragraph("*********************************************************************************************", f2));
			document.add(paragraph);
			return;
		}
		
		for (Project project : listProjects) {
			paragraph = new Paragraph("*********************************************************************************************", f2);
			paragraph.add(new Paragraph(" "));

			if ("false".equals(isAllFieldContacts)){
				paragraph.add(new Paragraph("Field contact: " + fieldContactFirstName + " " + fieldContactLastName,f2));
				paragraph.add(new Paragraph(" "));
			}

			paragraph.add(new Paragraph("Project: " + project.getName(),f2));

			if ("false".equals(isWholePeriod)){
				paragraph.add(new Paragraph(" "));
				paragraph.add(new Paragraph("From: " + strStartDate + "       To: " + strEndDate,f2));
			}

			if (project.getBeneficiary() != null){
				beneficiaryName = (project.getBeneficiary().getName() == null ? "" : project.getBeneficiary().getName());
			} else {
				beneficiaryName = "";
			}
			paragraph.add(new Paragraph(" ",f1));
			paragraph.add(new Paragraph("Beneficiary: " + beneficiaryName,f1));

			if (project.getFuUser() != null){
				firstName = (project.getFuUser().getFirstName() == null ? "" : project.getFuUser().getFirstName());
				lastName = (project.getFuUser().getLastName() == null ? "" : project.getFuUser().getLastName());
			} else {
				firstName = "";
				lastName = "";
			}
			paragraph.add(new Paragraph(" ",f1));
			paragraph.add(new Paragraph("FU: " + firstName + " " + lastName,f1));

			if (project.getFieldContactUser() != null){
				firstName = (project.getFieldContactUser().getFirstName() == null ? "" : project.getFieldContactUser().getFirstName());
				lastName = (project.getFieldContactUser().getLastName() == null ? "" : project.getFieldContactUser().getLastName());
			} else {
				firstName = "";
				lastName = "";
			}
			paragraph.add(new Paragraph("Field contact: " + firstName + " " + lastName,f1));
			table = new Table(4);
			table.getDefaultCell().setBorder(0);
			table.setBorder(0);
			totalAmount = BigDecimal.valueOf(0);
			for (Transaction transaction : project.getSortedTransactions()){
				if (("true".equals(isWholePeriod)) ||
						(transaction.getTradingDate().compareTo(startDate)>=0 && transaction.getTradingDate().compareTo(endDate)<=0)) {
					table.addCell(new SimpleDateFormat("dd.MM.yyyy").format(transaction.getTradingDate()));
					if (transaction.getIsIncome()) {
						table.addCell(transaction.getMember().getName());
					} else {
						table.addCell(transaction.getBeneficiary().getName());
					}
					if (transaction.getIsIncome()) {
						table.addCell(transaction.getAmountNOK().toString());
						totalAmount = totalAmount.add(transaction.getAmountNOK());
					} else {
						table.addCell(BigDecimal.valueOf(-1).multiply(transaction.getAmountNOK()).toString());
						totalAmount = totalAmount.subtract(transaction.getAmountNOK());
					}
					table.addCell("NOK");
				}
			}
			paragraph.add(new Paragraph("",f1));
			paragraph.add(table);
			paragraph.add(new Paragraph("_________________________________________________________________",f1));
			paragraph.add(new Paragraph("Total amount NOK                                                 " + totalAmount.toString() + " NOK",f1));
			paragraph.add(new Paragraph(" "));
			paragraph.add(new Paragraph(" "));
			document.add(paragraph);

		}

	}

}