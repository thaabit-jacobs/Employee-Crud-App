package com.employee;

import com.employee.models.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class EmployeeRepo {
    private List<Employee> employeeList;

    public EmployeeRepo(){
        employeeList = new ArrayList<>();

        employeeList.add(new Employee(Long.valueOf(101), "James", "Blunt", "james@gmail.com"));
        employeeList.add(new Employee(Long.valueOf(102), "Paul", "McCaugney", "paul@gmail.com"));
        employeeList.add(new Employee(Long.valueOf(103), "Peter", "Peter", "peter@gmail.com"));

        Comparator<Employee> byId = (e1, e2) -> e1.getEmId().intValue() - e2.getEmId().intValue();

        Collections.sort(employeeList, byId);
    }

    public List<Employee> getEmployeeList(){
        return new ArrayList<>(employeeList);
    }

    public boolean addEmployee(Employee employee){
        return employeeList.add(employee);
    }

    public boolean deleteEmployee(Long emId){
        return employeeList.removeIf(e -> e.getEmId() == emId);
    }

    public Employee updateEmployee(Long emId,String firstName, String lastName, String email){
        Employee employee = null;

        for (Employee employee1: employeeList){
            if(employee1.getEmId() == emId){
                employee = employee1;
                employee1.setEmFirstName(firstName);
                employee1.setEmLastName(lastName);
                employee1.setEmEmail(email);
            }
        }

        return employee;
    }

    public Long generateId(){
        return employeeList.get(employeeList.size() - 1).getEmId() + 1;
    }
}
