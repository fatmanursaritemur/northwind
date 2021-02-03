package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Category;
import java.util.List;

public interface ICategoryService {

  List<Category> getAll();

  Category findById(int categoryId);

  Category update(Category category);

  void delete(Category category);

  Category save(Category category);

  List<Category> getCategoriesOrderByDesc(String columnName);

  List<Category> getCategoriesOrderByAsc(String columnName);

}
