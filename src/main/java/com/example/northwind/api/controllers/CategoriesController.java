package com.example.northwind.api.controllers;

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.entities.concretes.Category;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CategoriesController {
  @Autowired
  ICategoryService categoryService;

  @GetMapping("/categories")
  public List<Category> getAll() {
    return categoryService.getAll();
  }

  @GetMapping("/categoriesOrderByDesc/{columnName}")
  public List<Category> getCategoriesOrderByDesc(@PathVariable(value = "columnName") String columnName) {
    return categoryService.getCategoriesOrderByDesc(columnName);
  }

  @GetMapping("/getCategoriesOrderByAsc/{columnName}")
  public List<Category> getCategoriesOrderByAsc(@PathVariable(value = "columnName") String columnName) {
    return categoryService.getCategoriesOrderByAsc(columnName);
  }

  @GetMapping("/categories/{id}")
  public Category getCategoryById(@PathVariable("id") int id) {
    return categoryService.findById(id);
  }

  @PostMapping("/categories")
  public Category save(@Valid @RequestBody Category category) {
    return categoryService.save(category);
  }

  @DeleteMapping("/categories/{id}")
  public void delete(@PathVariable(value = "id") int id) {
    Category deletedCategory = categoryService.findById(id);
    categoryService.delete(deletedCategory);
  }

  @PutMapping("/categories/{id}")
  public Category update(@PathVariable(value = "id") int id, @Valid @RequestBody Category category) {
    category.setId(id);
    return categoryService.update(category);
  }
}
