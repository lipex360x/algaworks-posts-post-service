package com.algaworks.posts.post.service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ResultPostCost {
  private String postId;
  private Integer wordCount;
  private BigDecimal calculatedValue;
}
