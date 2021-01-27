package com.employee;

import com.employee.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;


@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeRepository employeeRepo;
    private Long emId;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @GetMapping
    public String getAllEmployees(Model model){

        model.addAttribute("employeeList", employeeRepo.findAll());

        return "Employees.html";
    }

    @PostMapping
    public String processEmployeeSubmission(Employee employee){

        employeeRepo.save(employee);

        log.info("created emplyeee: " + employee);


        return "redirect:/employee";
    }

    @GetMapping("/delete/{emId}")
    public String deleteEmployee(@PathVariable Long emId){
        log.info("Id to be deleted: " + emId);

        employeeRepo.deleteById(emId);

        return "redirect:/employee";
    }

    @GetMapping("/update/{emId}")
    public String updatePath(@PathVariable Long emId){
        this.emId = emId;

        return "UpdateEmployee";
    }

    @Transactional
    @GetMapping("/updated")
    public String updateEmployee(@RequestParam(name="firstName") String firstName,
                                 @RequestParam(name="lastName") String lastName,
                                 @RequestParam(name="email") String email){

    employeeRepo.updateEmployeeInfo(firstName, lastName, email, this.emId);


    return "redirect:/employee";
    }
}
