package com.bojesenmark.security.student.controller;

import com.bojesenmark.security.student.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentManagementController {


    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "James Bond"),
        new Student(2, "Frank Hvam"),
        new Student(3, "Casper Christensen")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("Register: " + student);
    }


    @DeleteMapping(path = "{studentId")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println("Delete: " + studentId);
    }

    @PutMapping(path = "{studentId")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.println(String.format("%s %s", studentId, student));
    }
}
