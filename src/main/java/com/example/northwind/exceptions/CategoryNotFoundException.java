package com.example.northwind.exceptions;

public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException(int id) {
    super(String.format("%d'li kategori kayıtlı değil", id));
  }
}