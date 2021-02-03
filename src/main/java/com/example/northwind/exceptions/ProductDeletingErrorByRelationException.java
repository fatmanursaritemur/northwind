package com.example.northwind.exceptions;

public class ProductDeletingErrorByRelationException extends RuntimeException {

  public ProductDeletingErrorByRelationException(int id) {

    super(String.format("%d'li ürün order_details tablosuyla ilişkili",id));
  }}
