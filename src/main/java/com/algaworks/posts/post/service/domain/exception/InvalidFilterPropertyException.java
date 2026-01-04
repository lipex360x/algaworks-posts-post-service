package com.algaworks.posts.post.service.domain.exception;

public class InvalidFilterPropertyException extends DomainException {

  public InvalidFilterPropertyException(String filter) {
    super(String.format("invalid param field: %s", filter));
  }

}
