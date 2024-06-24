package com.example.demo.mapper;

import com.example.demo.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {

        //
       Integer a = resultSet.getInt("id");
       String b = resultSet.getString("name");

       //
       Student student = new Student();
       student.setId(a);
       student.setName(b);

       return student;
    }
}
