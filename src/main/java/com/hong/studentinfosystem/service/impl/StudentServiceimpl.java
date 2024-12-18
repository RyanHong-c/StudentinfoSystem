package com.hong.studentinfosystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hong.studentinfosystem.mapper.CategoryMapper;
import com.hong.studentinfosystem.mapper.StudentMapper;
import com.hong.studentinfosystem.pojo.Category;
import com.hong.studentinfosystem.pojo.PageBean;
import com.hong.studentinfosystem.pojo.Student;
import com.hong.studentinfosystem.pojo.User;
import com.hong.studentinfosystem.service.StudentService;
import com.hong.studentinfosystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void add(Student student) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        student.setCreateUser(id);
        studentMapper.add(student);
    }

    @Override
    public Student findById(Integer id) {
        Student s = studentMapper.findById(id);
        return s;
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void delete(Student student) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        student.setCreateUser(id);

        studentMapper.delete(student);
    }

    @Override
    public PageBean<Student> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Student> pb = new PageBean<>();

        PageHelper.startPage(pageNum,pageSize);

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        List<Student> l = studentMapper.list(id,categoryId,state);
        Page<Student> p = (Page<Student>)l;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

}
