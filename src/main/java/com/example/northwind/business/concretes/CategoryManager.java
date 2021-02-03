package com.example.northwind.business.concretes;

import com.example.northwind.UpdateColumnUtil;
import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.dataAccess.concretes.CategoryRepository;
import com.example.northwind.entities.concretes.Category;
import com.example.northwind.exceptions.CategoryDeletingErrorByRelationException;
import com.example.northwind.exceptions.CategoryNotFoundException;
import com.example.northwind.exceptions.ProductNotFoundException;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category findById(int categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryNotFoundException(categoryId));
  }

  @Override
  public Category update(Category category) {
    Category target = categoryRepository.findById(category.getId())
        .orElseThrow(() -> new ProductNotFoundException(category.getId()));
    BeanUtils.copyProperties(category, target, UpdateColumnUtil.getNullPropertyNames(category));
    return categoryRepository.save(target);
  }

  @Override
  public void delete(Category category) {
    try {
      categoryRepository.delete(category);
    } catch (DataIntegrityViolationException e) {
      throw  new CategoryDeletingErrorByRelationException(category.getId());
    }
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public List<Category> getCategoriesOrderByDesc(String columnName) {
    return categoryRepository.findAll(Sort.by(columnName).descending());
  }

  @Override
  public List<Category> getCategoriesOrderByAsc(String columnName) {
    return categoryRepository.findAll(Sort.by(columnName).ascending());
  }
}
