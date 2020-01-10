package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ua.petros.model.*;
import ua.petros.report.UserFields;
import ua.petros.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This Spring controller class implements a CSV file download functionality.
 * @author www.codejava.net
 *
 */
@Controller
public class CSVFileDownloadController {

	@Autowired
	private UserService userService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private BeneficiaryService beneficiaryService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ProjectMemberService projectMemberService;

    // Show reports page
    @RequestMapping(value = "/reports", method = {RequestMethod.GET})
    public String showReports(Model model){
        return "reports";
    }

	@RequestMapping(value = "/reports/users/csv", method = {RequestMethod.GET})
	public void downloadCSVusers(HttpServletResponse response, HttpServletRequest request,
								 @ModelAttribute("allFields") String allFields) throws IOException {

		List<String>userFields = new ArrayList<>();
		if (request.getParameterValues("userFields") == null){
			userFields = Collections.emptyList();
		} else {
			userFields = Arrays.asList(request.getParameterValues("userFields"));
		}

		String csvFileName = "users.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		List<User>users = userService.getAll();

		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		writer.write('\uFEFF'); // BOM for UTF-*
		// uses the Super CSV API to generate CSV data from the model data 
		/*ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
				CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);*/
		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer,
				CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

		Map<String,String>fieldMap = UserFields.fieldMap;
		String[] header = new String[fieldMap.size()];
		String[] fieldMapping = new String[fieldMap.size()];

		if (allFields.equals("1")){
			int i = 0;
			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
				header[i] = entry.getKey();
				fieldMapping[i++] = entry.getValue();
			}
		} else {
			int i = 0;
			for (Map.Entry<String, String> entry : UserFields.getFields(userFields).entrySet()) {
				header[i] = entry.getKey();
				fieldMapping[i++] = entry.getValue();
			}
		}

		csvWriter.writeHeader(header);

		for (User user : users) {
			csvWriter.write(user, fieldMapping);
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/members/csv", method = {RequestMethod.GET})
	public void downloadCSVMembers(HttpServletResponse response) throws IOException {

		String csvFileName = "donors.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		List<Member>members = memberService.getAll();

		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		writer.write('\uFEFF'); // BOM for UTF-*

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer,
				CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

		String[] header = { "Name", "Email",
				"Mobile", "City", "Account", "Bank", "DonorType" };

		csvWriter.writeHeader(header);

		for (Member member : members) {
			csvWriter.write(member, header);
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/beneficiaries/csv", method = {RequestMethod.GET})
	public void downloadCSVBeneficiaries(HttpServletResponse response) throws IOException {

		String csvFileName = "beneficiaries.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		List<Beneficiary>beneficiaries = beneficiaryService.getAll();

		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		writer.write('\uFEFF'); // BOM for UTF-*

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer,
				CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

		String[] header = { "Name", "Family", "Description",
				"Income", "Income Type", "Currency", "Born", "Field Contact" };

		String[] fieldmapping = { "Name", "Family", "Description",
				"Income", "IncomeType", "Currency", "Datefield", "User" };

		csvWriter.writeHeader(header);

		for (Beneficiary beneficiary : beneficiaries) {
			csvWriter.write(beneficiary, fieldmapping);
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/projects/csv", method = {RequestMethod.GET})
	public void downloadCSVProjects(HttpServletResponse response) throws IOException {

		String csvFileName = "projects.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		List<Project>projects = projectService.getAll();

		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		writer.write('\uFEFF'); // BOM for UTF-*

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer,
				CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

		String[] header = { "Name", "Balance NOK", "Start Date",
				"Stop Date", "Status", "FU", "Field Contact" };

		String[] fieldmapping = { "Name", "Balance", "StartDate",
				"StopDate", "Status", "fuUser", "fieldContactUser" };

		csvWriter.writeHeader(header);

		for (Project project : projects) {
			csvWriter.write(project, fieldmapping);
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/project/members/csv", method = {RequestMethod.GET})
	public void downloadCSVProjectMembers(HttpServletResponse response) throws IOException {

		String csvFileName = "projectDonors.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		List<ProjectMember>projectMembers = projectMemberService.getAll();
		projectMembers.sort(Comparator.naturalOrder());

		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		writer.write('\uFEFF'); // BOM for UTF-*

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer,
				CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

		String[] header = { "Project", "Donor", "Pledge", "Start Pledge",	"Stop Pledge" };

		String[] fieldmapping = { "Project", "Member", "Pledge", "StartPledge",	"StopPledge" };

		csvWriter.writeHeader(header);

		for (ProjectMember projectMember : projectMembers) {
			csvWriter.write(projectMember, fieldmapping);
		}

		csvWriter.close();
	}


	@RequestMapping(value = "/reports/transactions/csv", method = {RequestMethod.GET})
	public void downloadCSVTransactions(HttpServletResponse response) throws IOException {

		String csvFileName = "transactions.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		List<Transaction>transactions = transactionService.getAll();
		transactions.sort(Comparator.naturalOrder());

		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		writer.write('\uFEFF'); // BOM for UTF-*

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer,
				CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

		String[] header = { "Project", "Date", "Amount",
				"Currency", "Amount NOK", "From Donor", "To Beneficiary", "Transaction Type" };

		String[] fieldmapping = { "project", "tradingDate", "amount",
				"currency", "amountNOK", "member", "beneficiary", "transactionType" };

		csvWriter.writeHeader(header);

		for (Transaction transaction : transactions) {
			csvWriter.write(transaction, fieldmapping);
		}

		csvWriter.close();
	}
}