package com.test.springbootmaster.service;

import com.test.springbootmaster.entity.Department;
import com.test.springbootmaster.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Information Technology")
                .departmentAddress("Galle")
                .departmentCode("IT-07")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("Information Technology"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get data based on valid department name")
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "Information Technology";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName,found.getDepartmentName());
    }
}