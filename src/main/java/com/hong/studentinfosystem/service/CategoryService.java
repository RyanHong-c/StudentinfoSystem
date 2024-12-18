package com.hong.studentinfosystem.service;


import com.hong.studentinfosystem.pojo.Category;

import java.util.List;

public interface CategoryService {

    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    void update(Category category);

    void delete(Category category);
}
