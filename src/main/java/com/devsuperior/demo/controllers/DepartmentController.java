package com.devsuperior.demo.controllers;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.demo.dto.DepartmentDTO;
import com.devsuperior.demo.services.DepartmentService;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        List<DepartmentDTO> dto = departmentService.findAll();
        dto.sort(Comparator.comparing(DepartmentDTO::getName));
        return ResponseEntity.ok().body(dto);
    }
}
