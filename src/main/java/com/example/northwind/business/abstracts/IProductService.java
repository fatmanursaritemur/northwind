package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Product;
import java.util.List;

public interface IProductService {

  List<Product> getAll();

  Product findById(int productId) ;

  Product update(Product product);

  void delete(Product product);

  Integer countProductByCategoryId(Product product);

  boolean isCategoryFull(Product product);

  Product save(Product product);
}
