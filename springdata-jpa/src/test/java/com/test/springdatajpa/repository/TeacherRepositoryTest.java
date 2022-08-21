package com.test.springdatajpa.repository;

import com.test.springdatajpa.entity.Course;
import com.test.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository repository;

    @Test
    public void saveTeacher(){

        Course coursePsww = Course.builder()
                .title("PSWW")
                .credit(7)
                .build();

        Course courseOracle = Course.builder()
                .title("Oracle DB")
                .credit(9)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Himali")
                .lastName("erangika")
//                .courses(List.of(courseOracle,coursePsww))
                .build();

        repository.save(teacher);
    }
}