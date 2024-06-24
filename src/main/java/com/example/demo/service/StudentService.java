package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {

    Student getById(Integer studentId);

    void deleteById(Integer studentId);

    void batchInsert(List<Student> studentList);

    void insert(Student student);
}
