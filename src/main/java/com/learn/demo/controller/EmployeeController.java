package com.learn.demo.controller;

import com.learn.demo.controller.mapper.EmployeeMapper;
import com.learn.demo.controller.mapper.EntrepriseMapper;
import com.learn.demo.controller.mapper.PhoneNumberMapper;
import com.learn.demo.model.Employee;
import com.learn.demo.model.SearchForm;
import com.learn.demo.repository.entity.EmployeeEntity;
import com.learn.demo.repository.entity.EntrepriseEntity;
import com.learn.demo.repository.entity.PhoneNumberEntity;
import com.learn.demo.service.CSVService;
import com.learn.demo.service.EmployeeService;
import com.learn.demo.service.EntrepriseService;
import com.learn.demo.service.PhoneNumberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @GetMapping(path = "/employees/csv")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse, @ModelAttribute List<Employee> employeeList) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvService.writeEmployeesToCsv(servletResponse.getWriter(), employeeList.stream().map(employeeMapper::toDomain).toList());
    }

    @ModelAttribute("entreprise")
    public EntrepriseEntity getEntreprise() {
        return entrepriseService.getById(1L);
    }

    @GetMapping("/employees")
    public String employeeList(Model model, @RequestParam(value = "filterBy", required = false) String filterBy,
                               @RequestParam(value = "order", required = false) String order,
                               @RequestParam(value = "sex", required = false) String sex, @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "hiringDate", required = false) LocalDate hiringDate, @RequestParam(value = "departureDate", required = false) LocalDate departureDate) {
        log.error("SEX ORDER");
        if(Objects.equals(order, null)) {
            order = "ASC";
        }
        if(Objects.equals(sex, null)){
            sex = "All";
        }
        if(Objects.equals(departureDate, null)){
            departureDate = LocalDate.now();
        }
        System.out.println(order);
        System.out.println(sex);
        System.out.println(departureDate);
        List<EmployeeEntity> employeeList = employeeService.filterEmployees(filterBy,
                order, sex, keyword, hiringDate, departureDate);
        model.addAttribute("employees", employeeList.stream()
                .map(employeeEntity -> employeeMapper.toModel(employeeEntity))
                .toList());
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
//            Employee.Sex.valueOf(sex);
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
