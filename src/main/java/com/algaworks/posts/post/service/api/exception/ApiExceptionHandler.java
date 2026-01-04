package com.algaworks.posts.post.service.api.exception;

import com.algaworks.posts.post.service.domain.exception.EntityNotFoundException;
import com.algaworks.posts.post.service.domain.exception.InvalidFilterPropertyException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler({
    SocketTimeoutException.class,
    ConnectException.class,
    ClosedChannelException.class
  })
  public ProblemDetail handle(IOException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.GATEWAY_TIMEOUT);
    problemDetail.setTitle("Gateway timeout");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/gateway-timeout"));
    return problemDetail;
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handler(Exception ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    problemDetail.setTitle("Internal server error");
    problemDetail.setDetail("An error occurred while processing the request.");
    problemDetail.setType(URI.create("/error/server-error"));
    return problemDetail;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ProblemDetail handle(EntityNotFoundException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problemDetail.setTitle("Entity not found");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/entity-not-found"));
    return problemDetail;
  }

  @ExceptionHandler(InvalidFilterPropertyException.class)
  public ProblemDetail handle(InvalidFilterPropertyException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Invalid sort parameter");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/invalid-sort"));
    return problemDetail;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handle(
    MethodArgumentNotValidException ex,
    HttpServletRequest request
  ) {
    ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problem.setType(URI.create("/errors/invalid-request"));
    problem.setTitle("Invalid request");
    problem.setDetail("One or more fields are invalid");
    problem.setInstance(URI.create(request.getRequestURI()));
    List<Map<String, Object>> errors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(this::toFieldError)
      .toList();
    problem.setProperty("errors", errors);
    return problem;
  }

  private Map<String, Object> toFieldError(FieldError fe) {
    assert fe.getDefaultMessage() != null;
    return Map.of(
      "field", fe.getField(),
      "message", fe.getDefaultMessage()
    );
  }

}
