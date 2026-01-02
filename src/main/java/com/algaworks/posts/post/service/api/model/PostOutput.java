package com.algaworks.posts.post.service.api.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class PostOutput {
  private UUID id;
  private String author;
  private String title;
  private String body;
  private Integer wordCount;
  private BigDecimal calculatedValue;
}
