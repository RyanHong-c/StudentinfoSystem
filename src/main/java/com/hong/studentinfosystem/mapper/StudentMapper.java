package com.hong.studentinfosystem.mapper;

import com.hong.studentinfosystem.pojo.Category;
import com.hong.studentinfosystem.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Insert("insert into student(name,age,address,state,student.category_id,create_user,create_time,update_time)" +
            " values(#{name},#{age},#{address},#{state},#{categoryId},#{createUser},now(),now())")
    void add(Student student);

    @Select("select * from student where id=#{id}")
    Student findById(Integer id);

    @Update("update student set name=#{name},age=#{age},address=#{address},update_time=now() where id=#{id}")
    void update(Student student);

    @Delete("delete from student where id=#{id}")
    void delete(Student student);

    List<Student> list(Integer id, Integer categoryId, String state);
}
