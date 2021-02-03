package com.example.northwind.exceptions;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(int id) {
    super(String.format("Product with Id %d not found", id));
  }
}