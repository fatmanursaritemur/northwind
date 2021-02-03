package com.example.northwind.exceptions;

public class CategoryOutOfBoundsException extends RuntimeException {

  public CategoryOutOfBoundsException() {
    super("Kategoride maksimum ürün var");
  }
}
