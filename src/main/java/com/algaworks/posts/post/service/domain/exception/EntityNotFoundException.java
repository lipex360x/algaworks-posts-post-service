package com.algaworks.posts.post.service.domain.exception;

import java.util.UUID;

public class EntityNotFoundException extends DomainException {
  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(UUID postId) {
    this(String.format("post id %s not found", postId.toString()));
  }
}
