package com.pichincha.fabianOrdonez_inventarios.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Log4j2
@ControllerAdvice
public class ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ResourceNotFoundException.class, EmptyResultDataAccessException.class})
  public ResponseEntity<ResponseErrorStructure> handleResourceNotFound(Exception exception){
    log.error(exception.getMessage());
    return new ResponseEntity<>(
        ResponseErrorStructure.builder()
            .title("NOT FOUND")
            .message(exception.getMessage())
            .exceptionCode(HttpStatus.NOT_FOUND.toString())
            .build(),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(value = {
      MethodArgumentTypeMismatchException.class,
      MethodArgumentNotValidException.class,
      HttpMessageNotReadableException.class
  })
  public ResponseEntity<ResponseErrorStructure> handleBadRequest(Exception exception){
    log.error(exception.getMessage());
    return new ResponseEntity<>(
        ResponseErrorStructure.builder()
            .title("BAD REQUEST")
            .message(exception.getMessage())
            .exceptionCode(HttpStatus.BAD_REQUEST.toString())
            .build(),
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(value = {ConflictException.class})
  public ResponseEntity<ResponseErrorStructure> handleConflict(Exception exception){
    log.error(exception.getMessage());
    return new ResponseEntity<>(
        ResponseErrorStructure.builder()
            .title("CONFLICT")
            .message(exception.getMessage())
            .exceptionCode(HttpStatus.CONFLICT.toString())
            .build(),
        HttpStatus.CONFLICT
    );
  }
}