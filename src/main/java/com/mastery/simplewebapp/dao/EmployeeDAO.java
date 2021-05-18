package com.mastery.simplewebapp.dao;

import com.mastery.simplewebapp.service.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

     public List<Employee> index() {
        return jdbcTemplate.query("SELECT * FROM employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee show(long id) {
        return jdbcTemplate.query("SELECT * FROM employee WHERE id=?", new Object[]{id},new BeanPropertyRowMapper<>(Employee.class))
                .stream().findAny().orElse(null);
    }

    public void save(Employee employee) {
        jdbcTemplate.update("INSERT INTO employee VALUES(?,?,?)", employee.getId(), employee.getFirstName(),
                employee.getGender());
    }

    public void update(long id, Employee updateEmployee) {
        jdbcTemplate.update("UPDATE employee SET firstName=?, gender=?, WHERE id=?", updateEmployee.getFirstName(),
                updateEmployee.getGender(), id);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);
    }
}
