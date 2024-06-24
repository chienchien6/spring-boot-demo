package com.example.demo.dao;

import com.example.demo.model.Student;
import com.example.demo.mapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentImpl implements StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Student getById(Integer studentId) {
        String sql = "SELECT id,name FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        List<Student> list = namedParameterJdbcTemplate.query(sql,map,new StudentRowMapper());

        if(list.size() > 0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer studentId) {
        String sql = "DELETE FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void insert(Student student) {
        String sql = "INSERT INTO student(name)VALUE(:studentName)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("mysql自動生成的id為:"+id);

    }

    @Override
    public void insertList(List<Student> studentList) {

        String sql = "INSERT INTO student(name) VALUES(:studentName)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];

        for(int i = 0; i < studentList.size(); i++){
            Student student = studentList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);

    }
}
