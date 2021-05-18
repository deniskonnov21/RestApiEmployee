package com.mastery.simplewebapp.restController;

import com.mastery.simplewebapp.dao.EmployeeDAO;
import com.mastery.simplewebapp.service.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> index() {
        List<Employee> employeeList = this.employeeDAO.index();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Employee> show(@PathVariable("id") long employeeId) {
        Employee employee = this.employeeDAO.show(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<Employee> save(Employee employee) {
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.employeeDAO.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") long employeeId, Employee updateEmployee) {
        Employee employee = this.employeeDAO.show(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.employeeDAO.update(employeeId, updateEmployee);
        return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> delete(@PathVariable("id") long employeeId) {
        Employee employee = this.employeeDAO.show(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.employeeDAO.delete(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
