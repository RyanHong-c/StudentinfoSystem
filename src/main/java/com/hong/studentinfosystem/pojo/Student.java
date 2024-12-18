package com.hong.studentinfosystem.pojo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    @NotNull(groups = Category.Update.class)
    private String id;

    @NotEmpty
    private String name;
    private int age;

    @NotEmpty
    private String address;
    private String state;//发布状态 已发布|草稿
    private Integer categoryId;//班级分类id

    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }

}
