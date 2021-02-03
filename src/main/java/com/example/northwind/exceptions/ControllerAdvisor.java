package com.example.northwind.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

  private static final String MESSAGE = "message";
  private static final String TIMESTAMP = "timestamp";

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(
      NotFoundException ex, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put(TIMESTAMP, LocalDateTime.now());
    body.put(MESSAGE, ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(CategoryOutOfBoundsException.class)
  public ResponseEntity<Object> handleCategoryOutOfBoundsException(
      CategoryOutOfBoundsException ex, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put(TIMESTAMP, LocalDateTime.now());
    body.put(MESSAGE, ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DeletingErrorByRelationException.class)
  public ResponseEntity<Object> handleDeletingErrorByRelationException(
      DeletingErrorByRelationException ex, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put(TIMESTAMP, LocalDateTime.now());
    body.put(MESSAGE, ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put(TIMESTAMP, LocalDate.now());
    body.put("status", status.value());

    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(x -> x.getDefaultMessage())
        .collect(Collectors.toList());

    body.put("errors", errors);

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}
