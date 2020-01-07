package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ua.petros.model.Beneficiary;
import ua.petros.model.Member;
import ua.petros.model.User;
import ua.petros.service.BeneficiaryService;
import ua.petros.service.MemberService;
import ua.petros.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    // Show reports page
    @RequestMapping(value = "/reports", method = {RequestMethod.GET})
    public String showReports(Model model){
        return "reports";
    }

	@RequestMapping(value = "/reports/users", method = {RequestMethod.GET})
	public void downloadCSVusers(HttpServletResponse response) throws IOException {

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

		String[] header = { "Username", "First Name", "Last Name", "Email",
				"Mobile1", "Mobile2", "Address", "Account", "Bank", "Role" };

		String[] fieldMapping = new String[] { "Username", "FirstName", "LastName", "Email",
				"Mobile1", "Mobile2", "Address", "Account", "Bank", "Roles" };

		csvWriter.writeHeader(header);

		for (User user : users) {
			csvWriter.write(user, fieldMapping);
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/members", method = {RequestMethod.GET})
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

	@RequestMapping(value = "/reports/beneficiaries", method = {RequestMethod.GET})
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
}