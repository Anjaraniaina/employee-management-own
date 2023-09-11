package com.learn.demo.controller;

import com.learn.demo.controller.mapper.EmployeeMapper;
import com.learn.demo.controller.mapper.EntrepriseMapper;
import com.learn.demo.controller.mapper.PhoneNumberMapper;
import com.learn.demo.model.Employee;
import com.learn.demo.repository.entity.EmployeeEntity;
import com.learn.demo.repository.entity.EntrepriseEntity;
import com.learn.demo.service.CSVService;
import com.learn.demo.service.EmployeeService;
import com.learn.demo.service.EntrepriseService;
import com.learn.demo.service.PhoneNumberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private EmployeeService employeeService;
    private EntrepriseService entrepriseService;
    private PhoneNumberService phoneNumberService;
    private PhoneNumberMapper phoneNumberMapper;
    private EntrepriseMapper entrepriseMapper;
    private EmployeeMapper employeeMapper;
    private CSVService csvService;
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

        @GetMapping("/export")
        public StreamingResponseBody exportToCSV(
                @RequestParam(name = "firstName", required = false) String firstName,
                @RequestParam(name = "lastName", required = false) String lastName,
                @RequestParam(name = "function", required = false) String function,
                @RequestParam(name = "sex", required = false) String sex,
                @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
                @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection,
                HttpServletResponse response
        ) {

            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortDirection), orderBy);

            com.learn.demo.model.Employee.Sex selectedSex = null;
            if (!"".equals(sex)) {
                selectedSex = com.learn.demo.model.Employee.Sex.valueOf(sex);
            }

            List<Employee> employees = employeeService.getFilteredAndSortedEmployees(
                    firstName, lastName, function, selectedSex, order
            ).stream().map(employee -> employeeMapper.toModel(employee)).toList();

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=employees.csv");

            StreamingResponseBody stream = outputStream -> {
                Writer writer = new OutputStreamWriter(outputStream);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "First Name",
                                "Last Name","Address","Children",
                                "Birthdate","Email Perso","Email Pro",
                                "CIN Delivrance Location","CIN Issue Date","CIN Number",
                                "CNAPS Number","Function","Hiring Date",
                                "Departure Date","Phone Numbers","Sex",
                                "Socio Pro Category","Matricule","Image"));

                for (Employee employee : employees) {
                    csvPrinter.printRecord(employee.getId(), employee.getFirstName(),
                            employee.getLastName(), employee.getAddress(), employee.getChildren(),
                            employee.getBirthDate(), employee.getEmailPerso(), employee.getEmailPro(),
                            employee.getCINDelivranceLocation(), employee.getCINIssueDate(), employee.getCINNumber(),
                            employee.getCnapsNumber(), employee.getFunction(), employee.getHiringDate(),
                            employee.getDepartureDate(), employee.getPhoneNumber(), employee.getSex(),
                            employee.getSocioProCategory(), employee.getMatricule(), employee.getImage());
                }

                csvPrinter.flush();
                csvPrinter.close();
            };
            return stream;
        }


    @ModelAttribute("entreprise")
    public EntrepriseEntity getEntreprise() {
        return entrepriseService.getById(1L);
    }

    @GetMapping("/employees")
    public String getFilteredEmployees(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "function", required = false) String function,
            @RequestParam(name = "sex", required = false) String sex,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection,
            Model model
    ) {
        Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortDirection), orderBy);

        com.learn.demo.model.Employee.Sex selectedSex = null;
        if (sex != null && !sex.isEmpty()) {
            try {
                selectedSex = com.learn.demo.model.Employee.Sex.valueOf(sex);
            } catch (IllegalArgumentException ex) {
                log.info(sex);
            }
        }

        List<Employee> employees = employeeService.getFilteredAndSortedEmployees(
                firstName, lastName, function, selectedSex, order
        ).stream().map(employee -> employeeMapper.toModel(employee)).toList();

        model.addAttribute("employees", employees);
        return "employeelist.html";
    }

    @GetMapping("/employee")
    public String getEmployee(@RequestParam Long employeeId, Model model) {
        EmployeeEntity employee = employeeService.getById(employeeId);
        model.addAttribute("employee", employeeMapper.toModel(employee));
        return "employee.html";
    }

    @GetMapping("/entreprise")
    public String getEntreprise(Model model) {
        EntrepriseEntity entreprise = entrepriseService.getById(1L);
        model.addAttribute("entreprise", entrepriseMapper.toModel(entreprise));
        return "fragments/navbar.html";
    }

    @GetMapping("/employee-form")
    public String employeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeform.html";
    }

    @GetMapping("/employee-form/edit")
    public String employeeFormEdit(@RequestParam Long employeeId, Model model) {
        Employee employee = employeeMapper.toModel(employeeService.getById(employeeId));
        model.addAttribute("employee", employee);
        System.out.println(employee);
        return "employeeformedit.html";
    }

    @PostMapping("/employees")
    public String createOrUpdateEmployee(@ModelAttribute("employee") Employee employee,
                                         @RequestParam(value = "employeeId", required = false) Long employeeId,
                                         @RequestParam(value = "phoneNumbers", required = false) String phoneNumbers,
//                                         @RequestParam(value = "sex", required = false) String sex,
//                                         @RequestParam(value = "socioProCategory", required = false) String socioProCategory,
                                         @RequestParam(value = "file", required = false) MultipartFile file
                                         ) throws IOException {
        if (employeeId != null && file == null) {
            employee.setImage(employeeService.getById(employeeId).getImage());
        } else if(file != null){
            employee.setImage(employeeService.imageFileToBase64(file));
        }
//        if (sex != null) {
//            com.learn.demo.model.Employee.Sex.valueOf(sex);
//        }
//        if (socioProCategory != null) {
//            employee.setSocioProCategory(Employee.CSP.valueOf(socioProCategory));
//        }
//        boolean b = phoneNumbers != null && employeeService.isUnique(Arrays.stream(phoneNumbers.split(",")).toList());
//        if ( phoneNumbers != null && b){
//            employee.setPhoneNumber(phoneNumbers);
//        }

        employee.setPhoneNumber(phoneNumbers);
        employeeService.addEmployee(employeeMapper.toDomain(employee));
        return "redirect:/employees";
    }
}
