package com.algaworks.posts.post.service.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(InvalidRequestException.class)
  public ProblemDetail handle(InvalidRequestException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Bad request");
    problemDetail.setDetail("invalid field: " + e.getMessage());
    problemDetail.setType(URI.create("/errors/bad-request"));
    return problemDetail;
  }
}
