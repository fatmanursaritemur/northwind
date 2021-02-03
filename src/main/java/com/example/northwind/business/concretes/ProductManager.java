package com.example.northwind.business.concretes;

import com.example.northwind.UpdateColumnUtil;
import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.dataAccess.concretes.ProductRepository;
import com.example.northwind.entities.concretes.Product;
import com.example.northwind.exceptions.CategoryOutOfBoundsException;
import com.example.northwind.exceptions.ProductDeletingErrorByRelationException;
import com.example.northwind.exceptions.ProductNotFoundException;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProductManager implements IProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public List<Product> getAll() {
    return productRepository.findAll();
  }

  @Override
  public Product findById(int productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(productId));
  }

  @Override
  public Product update(Product product) {
    Product target = productRepository.findById(product.getId())
        .orElseThrow(() -> new ProductNotFoundException(product.getId()));
    BeanUtils.copyProperties(product, target, UpdateColumnUtil.getNullPropertyNames(product));
    return productRepository.save(target);
  }

  @Override
  public void delete(Product product)  {
    try {
      productRepository.delete(product);
    } catch (DataIntegrityViolationException e) {
      throw  new ProductDeletingErrorByRelationException(product.getId());
    }
  }

  @Override
  public Product save(Product product) {
    if (!isCategoryFull(product)) {
      return productRepository.save(product);
    } else {
      throw new CategoryOutOfBoundsException();
    }
  }
  @Override
  public Integer countProductByCategoryId(Product product) {
    return productRepository.countProductByCategoryId(product.getCategoryId());
  }

  @Override
  public boolean isCategoryFull(Product product) {
    return countProductByCategoryId(product) >= 10 ? true : false;
  }

}
