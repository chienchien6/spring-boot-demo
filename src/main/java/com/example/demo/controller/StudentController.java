package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public String insert(@RequestBody Student student){
        studentService.insert(student);
        return "insert success";
    }

    @PostMapping("/students/batch")
    public String insertList(@RequestBody List<Student> studentList){
      studentService.batchInsert(studentList);
        return"執行一批INSERT sql";
    }

    @DeleteMapping("students/{studentId}")
    public String delete(@PathVariable Integer studentId){
        studentService.deleteById(studentId);
        return "delete success";
    }

    @GetMapping("/students/{studentId}")
    public Student select(@PathVariable Integer studentId){
        return studentService.getById(studentId);
    }
}
