package com.learn.demo.controller;

import com.learn.demo.model.Employee;
import com.learn.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;
    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @GetMapping("/employees")
    public String employeeList(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployee();
        model.addAttribute("employees", employeeList);
        return "employeelist.html";
    }

    @GetMapping("/employee")
    public String getEmployee(@RequestParam Long employeeId, Model model) {
        Employee employee = employeeService.getById(employeeId);
        model.addAttribute("employee", employee);
        return "employee.html";
    }

    @GetMapping("/employee-form")
    public String employeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeform.html";
    }

    @PostMapping("/employees")
    public String createOrUpdateEmployee(@ModelAttribute("employee") Employee employee,
                                         @RequestParam(value = "file", required = false) MultipartFile file,
                                         Model model) throws IOException {
        model.addAttribute("employee", employee);
        if (file != null) {
            employee.setImage(employeeService.imageFileToBase64(file));
        }
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }
}
