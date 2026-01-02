package com.algaworks.posts.post.service.api.exception;

public class InvalidRequestException extends RuntimeException {
  public InvalidRequestException(String input) {
    super(input);
  }
}
