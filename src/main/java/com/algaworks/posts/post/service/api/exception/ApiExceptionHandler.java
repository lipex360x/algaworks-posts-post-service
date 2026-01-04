package com.algaworks.posts.post.service.api.exception;

import com.algaworks.posts.post.service.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;

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

  @ExceptionHandler(InvalidRequestException.class)
  public ProblemDetail handle(InvalidRequestException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Bad request");
    problemDetail.setDetail("invalid field: " + e.getMessage());
    problemDetail.setType(URI.create("/errors/bad-request"));
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
}
