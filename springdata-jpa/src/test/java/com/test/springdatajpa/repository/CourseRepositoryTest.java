package com.test.springdatajpa.repository;

import com.test.springdatajpa.entity.Course;
import com.test.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository repository;

    @Test
    public void printCourses(){
        List<Course> courses = repository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Dimuthu")
                .lastName("suranga")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(5)
                .teacher(teacher)
                .build();

        repository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
            PageRequest.of(0,3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(2,2);

        List<Course> courses = repository.findAll(secondPageWithTwoRecords).getContent();

        Long totalElements = repository.findAll(secondPageWithTwoRecords).getTotalElements();

        int totalPages = repository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("Courses : " + courses);
        System.out.println("Total Elements : " + totalElements);
        System.out.println("Total pages : " + totalPages);

    }

}