package com.algaworks.posts.post.service.domain.exception;

public class DomainException extends RuntimeException {
  protected DomainException(String message) {
    super(message);
  }
}
