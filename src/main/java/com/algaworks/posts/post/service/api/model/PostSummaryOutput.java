package com.algaworks.posts.post.service.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostSummaryOutput {
  private UUID id;
  private String author;
  private String title;
  private String summary;
}
