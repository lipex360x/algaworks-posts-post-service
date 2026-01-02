package com.algaworks.posts.post.service.api.mapper;

import com.algaworks.posts.post.service.api.model.PostInput;
import com.algaworks.posts.post.service.api.model.PostSummaryOutput;
import com.algaworks.posts.post.service.common.UUIDGenerator;
import com.algaworks.posts.post.service.domain.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostAssembler {

  public PostSummaryOutput toSummaryModel(Post post) {
    return PostSummaryOutput.builder()
      .id(post.getId())
      .author(post.getAuthor())
      .title(post.getTitle())
      .body(post.getBody())
      .build();
  }

  public Post toEntity(PostInput input) {
    return Post.builder()
      .id(UUIDGenerator.generate())
      .author(input.getAuthor())
      .title(input.getTitle())
      .body(input.getBody())
      .build();
  }
}
