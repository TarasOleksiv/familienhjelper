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
import ua.petros.report.*;
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
		List<Project> listProjects = projectService.getAll();
		List<Project> sortedListProjects = listProjects.stream().sorted().collect(Collectors.toList());
		model.addAttribute("listProjects",sortedListProjects);
        return "reports";
    }

	@RequestMapping(value = "/reports/users/csv", method = {RequestMethod.GET})
	public void downloadCSVusers(HttpServletResponse response, HttpServletRequest request,
								 @ModelAttribute("allFields") String allFields) throws IOException {

		List<String>chosenFields = getChosenFields(request,"userFields");
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

		UserFields userFields = new UserFields();

		Map<String,String[]> map = getHeadersAndMappings(userFields,allFields,chosenFields);

		csvWriter.writeHeader(map.get("header"));

		for (User user : users) {
			csvWriter.write(user, map.get("fieldMapping"));
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/members/csv", method = {RequestMethod.GET})
	public void downloadCSVMembers(HttpServletResponse response, HttpServletRequest request,
								   @ModelAttribute("allFields") String allFields) throws IOException {

		List<String>chosenFields = getChosenFields(request,"memberFields");
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

		MemberFields memberFields = new MemberFields();
		Map<String,String[]> map = getHeadersAndMappings(memberFields,allFields,chosenFields);

		csvWriter.writeHeader(map.get("header"));

		for (Member member : members) {
			csvWriter.write(member, map.get("fieldMapping"));
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/beneficiaries/csv", method = {RequestMethod.GET})
	public void downloadCSVBeneficiaries(HttpServletResponse response, HttpServletRequest request,
										 @ModelAttribute("allFields") String allFields) throws IOException {

		List<String>chosenFields = getChosenFields(request,"beneficiaryFields");
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

		BeneficiaryFields beneficiaryFields = new BeneficiaryFields();
		Map<String,String[]> map = getHeadersAndMappings(beneficiaryFields,allFields,chosenFields);

		csvWriter.writeHeader(map.get("header"));

		for (Beneficiary beneficiary : beneficiaries) {
			csvWriter.write(beneficiary, map.get("fieldMapping"));
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/projects/csv", method = {RequestMethod.GET})
	public void downloadCSVProjects(HttpServletResponse response, HttpServletRequest request,
									@ModelAttribute("allFields") String allFields) throws IOException {

		List<String>chosenFields = getChosenFields(request,"projectFields");
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

		ProjectFields projectFields = new ProjectFields();
		Map<String,String[]> map = getHeadersAndMappings(projectFields,allFields,chosenFields);

		csvWriter.writeHeader(map.get("header"));

		for (Project project : projects) {
			csvWriter.write(project, map.get("fieldMapping"));
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/reports/project/members/csv", method = {RequestMethod.GET})
	public void downloadCSVProjectMembers(HttpServletResponse response, HttpServletRequest request,
										  @ModelAttribute("allFields") String allFields) throws IOException {

		List<String>chosenFields = getChosenFields(request,"projectMemberFields");
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

		ProjectMemberFields projectMemberFields = new ProjectMemberFields();
		Map<String,String[]> map = getHeadersAndMappings(projectMemberFields,allFields,chosenFields);

		csvWriter.writeHeader(map.get("header"));

		for (ProjectMember projectMember : projectMembers) {
			csvWriter.write(projectMember, map.get("fieldMapping"));
		}

		csvWriter.close();
	}


	@RequestMapping(value = "/reports/transactions/csv", method = {RequestMethod.GET})
	public void downloadCSVTransactions(HttpServletResponse response, HttpServletRequest request,
										@ModelAttribute("allFields") String allFields) throws IOException {

		List<String>chosenFields = getChosenFields(request,"transactionFields");
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

		TransactionFields transactionFields = new TransactionFields();
		Map<String,String[]> map = getHeadersAndMappings(transactionFields,allFields,chosenFields);

		csvWriter.writeHeader(map.get("header"));

		for (Transaction transaction : transactions) {
			csvWriter.write(transaction, map.get("fieldMapping"));
		}

		csvWriter.close();
	}

	private List<String> getChosenFields(HttpServletRequest request, String fieldsList){
		List<String>chosenFields = new ArrayList<>();
		if (request.getParameterValues(fieldsList) == null){
			chosenFields = Collections.emptyList();
		} else {
			chosenFields = Arrays.asList(request.getParameterValues(fieldsList));
		}

		return chosenFields;
	}

	private Map<String,String[]> getHeadersAndMappings(AbstractFields abstractFields, String allFields, List<String>chosenFields){
		Map<String,String>fieldMap = abstractFields.getFieldMap();
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
			for (Map.Entry<String, String> entry : abstractFields.getFields(chosenFields).entrySet()) {
				header[i] = entry.getKey();
				fieldMapping[i++] = entry.getValue();
			}
		}

		Map<String,String[]> map = new HashMap<>();
		map.put("header",header);
		map.put("fieldMapping",fieldMapping);
		return map;
	}
}