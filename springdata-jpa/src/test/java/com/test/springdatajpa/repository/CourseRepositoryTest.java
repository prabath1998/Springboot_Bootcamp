package com.test.springdatajpa.repository;

import com.test.springdatajpa.entity.Course;
import com.test.springdatajpa.entity.Student;
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
    public void printCourses() {
        List<Course> courses = repository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher() {
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
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(2, 2);

        List<Course> courses = repository.findAll(secondPageWithTwoRecords).getContent();

        Long totalElements = repository.findAll(secondPageWithTwoRecords).getTotalElements();

        int totalPages = repository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("Courses : " + courses);
        System.out.println("Total Elements : " + totalElements);
        System.out.println("Total pages : " + totalPages);

    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 4, Sort.by("title"));

        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses = repository.findAll(sortByTitle).getContent();

        System.out.println("Sort by title : " + courses);
    }


    @Test
    public void findByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0,10);

        List<Course> courses = repository.findByTitleContaining("D",firstPageTenRecords).getContent();

        System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();

        Course course = Course
                .builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        repository.save(course);
    }
}