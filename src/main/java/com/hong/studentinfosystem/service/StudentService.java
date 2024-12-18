package com.hong.studentinfosystem.service;

import com.hong.studentinfosystem.pojo.PageBean;
import com.hong.studentinfosystem.pojo.Student;

public interface StudentService {

    void add(Student student);

    Student findById(Integer id);

    void update(Student student);

    void delete(Student student);

    PageBean<Student> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
