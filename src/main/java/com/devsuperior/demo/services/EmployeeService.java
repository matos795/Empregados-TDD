package com.devsuperior.demo.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.entities.Employee;
import com.devsuperior.demo.repositories.DepartmentRepository;
import com.devsuperior.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAllPaged(Pageable pageable) {
        Page<Employee> listPage = employeeRepository.findAll(pageable);

        List<Employee> sortedList = listPage.getContent().stream().sorted(Comparator.comparing(Employee::getName)).toList();
        
        Page<Employee> sortedPage = new PageImpl<>(
        sortedList,
        listPage.getPageable(),
        listPage.getTotalElements()
); 

        return sortedPage.map(x -> new EmployeeDTO(x));
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto) {
        Employee entity = new Employee();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setDepartment(departmentRepository.getReferenceById(dto.getDepartmentId()));

        entity = employeeRepository.save(entity);
        return new EmployeeDTO(entity);
    }
}
