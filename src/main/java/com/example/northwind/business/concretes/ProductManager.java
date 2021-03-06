package com.example.northwind.business.concretes;

import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.dataAccess.concretes.ProductRepository;
import com.example.northwind.entities.concretes.Product;
import com.example.northwind.exceptions.CategoryOutOfBoundsException;
import com.example.northwind.exceptions.DeletingErrorByRelationException;
import com.example.northwind.exceptions.NotFoundException;
import com.example.northwind.utilities.UpdateColumnUtil;
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
  public Product findById(int productId) throws NotFoundException {
    return productRepository.findById(productId)
        .orElseThrow(() -> new NotFoundException(Product.class.getSimpleName(), productId));
  }

  @Override
  public Product update(Product product) throws NotFoundException {

    Product target = productRepository.findById(product.getId())
        .orElseThrow(() -> new NotFoundException(Product.class.getSimpleName(), product.getId()));

    BeanUtils.copyProperties(product, target, UpdateColumnUtil.getNullPropertyNames(product));
    return productRepository.save(target);
  }

  @Override
  public void delete(Product product) throws DeletingErrorByRelationException {
    try {
      productRepository.delete(product);
    } catch (DataIntegrityViolationException e) {
      throw new DeletingErrorByRelationException(Product.class.getSimpleName(), product.getId());
    }
  }

  @Override
  public Product save(Product product) throws CategoryOutOfBoundsException {
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
