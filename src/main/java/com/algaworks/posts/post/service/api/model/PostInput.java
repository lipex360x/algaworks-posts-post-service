package com.algaworks.posts.post.service.api.model;

import lombok.Data;

@Data
public class PostInput {
  private String author;
  private String title;
  private String body;
}
