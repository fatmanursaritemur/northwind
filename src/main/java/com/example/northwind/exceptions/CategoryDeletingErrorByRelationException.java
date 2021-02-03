package com.example.northwind.exceptions;

public class CategoryDeletingErrorByRelationException extends RuntimeException{
  public CategoryDeletingErrorByRelationException(int id) {
    super(String.format("%d' idli kategori product tablosuyla ilişkili",id));
  }}

