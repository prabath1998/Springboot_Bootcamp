package com.test.springdatajpa.repository;

import com.test.springdatajpa.entity.Guardian;
import com.test.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("prabath@gmail.com")
                .firstName("prabath")
                .lastName("udayanga")
//                .guardianName("priyantha")
//                .guardianEmail("priya@gmail.com")
//                .guardianMobile("0778565223")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("samantha")
                .email("sama@gmail.com")
                .mobile("0223654784")
                .build();

        Student student = Student.builder()
                .firstName("janaka")
                .lastName("chathuranga")
                .emailId("jana@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
    }

    @Test
    public void getStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("janaka");
        System.out.println(students);
    }

    @Test
    public void getStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("p");
        System.out.println(students);
    }

    @Test
    public void getStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("priyantha");
        System.out.println(students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("prabath@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String firstName = studentRepository.getStudenFirstNametByEmailAddress("jana@gmail.com");
        System.out.println(firstName);
    }

    @Test
    public void printStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("prabath@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("jana@gmail.com");
        System.out.println(student);
    }


    @Test
    public void updateStudentByEmailIdTest(){
        studentRepository.updateStudentNameByEmailId("Janaka","jana@gmail.com");
    }

}