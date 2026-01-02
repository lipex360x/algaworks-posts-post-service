package com.algaworks.posts.post.service.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageToCalculatePostCost {
  private String postId;
  private String postBody;
}
