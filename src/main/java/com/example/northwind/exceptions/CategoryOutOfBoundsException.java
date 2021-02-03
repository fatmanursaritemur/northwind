package com.example.northwind.exceptions;

public class CategoryOutOfBoundsException extends RuntimeException {

  public CategoryOutOfBoundsException() {
    super(String.format("Kategoride maksimum ürün var"));
  }
}
