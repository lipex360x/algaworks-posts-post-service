package com.algaworks.posts.post.service.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostInput {

  @NotBlank(message = "author must be provided")
  private String author;

  @NotBlank(message = "title must be provided")
  private String title;

  @NotBlank(message = "body must be provided")
  private String body;
}
