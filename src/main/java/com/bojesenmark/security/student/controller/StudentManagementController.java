package com.bojesenmark.security.student.controller;

import com.bojesenmark.security.student.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students/")
public class StudentManagementController {


    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "James Bond"),
        new Student(2, "Frank Hvam"),
        new Student(3, "Casper Christensen")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        System.out.println("---- getAllStudents called");
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("---- registerNewStudent called -  Register: " + student);
    }


    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable(value = "studentId") Integer studentId) {
        System.out.println("---- deleteStudent called - Delete: " + studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable(value = "studentId") Integer studentId, @RequestBody Student student) {
        System.out.println(String.format("---- updateStudent called - %s %s", studentId, student));
    }
}
