package com.example.northwind.exceptions;

public class NotFoundException extends Exception {

  public NotFoundException(String className,int id) {
    super(String.format("%d'li %s bulunamadı", id, className));
  }
}