package com.hong.studentinfosystem.controller;


import com.hong.studentinfosystem.pojo.*;
import com.hong.studentinfosystem.service.CategoryService;
import com.hong.studentinfosystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentinfoController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated(Student.Add.class) Student student) {
        studentService.add(student);
        return Result.success("添加成功");
    }

/*
    @GetMapping("/list")
    public Result<List<Category>> list(){
        List<Category> cs = studentService.list();
        return Result.success(cs);
    }
*/

    @GetMapping("/detail")
    public Result<Student> detail(Integer id){
        Student student = studentService.findById(id);
        return Result.success(student);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Student.Update.class) Student student){
        studentService.update(student);
        return Result.success("更新成功");
    }

    @RequestMapping ("/delete")
    public Result delete(Student student){
        studentService.delete(student);
        return Result.success("删除成功");
    }

    @GetMapping("/list")
    public Result<PageBean<Student>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Student> pb = studentService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
}
