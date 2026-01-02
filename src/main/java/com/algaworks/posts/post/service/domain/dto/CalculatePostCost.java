package com.algaworks.posts.post.service.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculatePostCost {
  private String postId;
  private String postBody;
}
