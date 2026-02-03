package com.devsuperior.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.entities.Employee;

public class DepartmentDTO {

	private Long id;
	private String name;
	private List<EmployeeDTO> employees = new ArrayList<>();

    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDTO(Department entity) {
        id = entity.getId();
        name = entity.getName();
        for (Employee employee : entity.getEmployees()) {
            employees.add(new EmployeeDTO(employee));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }
}
