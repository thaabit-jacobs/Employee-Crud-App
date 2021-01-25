package com.employee;

import com.employee.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeController(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @GetMapping
    public String getAllEmployees(Model model){

        model.addAttribute("employeeList", employeeRepo.getEmployeeList());

        return "Employees.html";
    }

    @PostMapping
    public String processEmployeeSubmission(Employee employee){
        long newId = employeeRepo.generateId();

        employee.setEmId(newId);

        employeeRepo.addEmployee(employee);

        log.info("created emplyeee: " + employee);


        return "redirect:/employee";
    }

    @GetMapping("/delete/{emId}")
    public String deleteEmployee(@PathVariable Long emId){
        log.info("Id to be deleted: " + emId);

        employeeRepo.deleteEmployee(emId);

        return "redirect:/employee";
    }

    @GetMapping("/update/{emId}")
    public String updatePath(@PathVariable Long emId, HttpServletResponse response){

        Cookie cookie = new Cookie("emId", emId.toString());

        response.addCookie(cookie);

        return "UpdateEmployee";
    }

    @GetMapping("/updated")
    public String updateEmployee(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(name="firstName") String firstName,
                                 @RequestParam(name="lastName") String lastName,
                                 @RequestParam(name="email") String email){

        Long emId = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie: cookies){
            emId = Long.valueOf(cookie.getComment());
        }

        Cookie cookie = new Cookie("emId", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        Employee emplo = employeeRepo.updateEmployee(emId, firstName, lastName, email);

        log.info("updated emploee: " + emplo);

        return "redirect:/employee";
    }
}
