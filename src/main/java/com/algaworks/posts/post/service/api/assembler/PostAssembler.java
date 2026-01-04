package com.algaworks.posts.post.service.api.assembler;

import com.algaworks.posts.post.service.api.model.PostInput;
import com.algaworks.posts.post.service.api.model.PostOutput;
import com.algaworks.posts.post.service.api.model.PostSummaryOutput;
import com.algaworks.posts.post.service.common.UUIDGenerator;
import com.algaworks.posts.post.service.domain.entity.Post;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostAssembler {

  private final Integer MAX_SUMMARY_BODY_LINES = 3;

  public PostSummaryOutput toSummaryModel(Post post) {
    return PostSummaryOutput.builder()
      .id(post.getId())
      .author(post.getAuthor())
      .title(post.getTitle())
      .summary(summariseBody(post.getBody()))
      .build();
  }

  public PostOutput toModel(Post post) {
    return PostOutput.builder()
      .id(post.getId())
      .author(post.getAuthor())
      .title(post.getTitle())
      .wordCount(post.getWordCount())
      .calculatedValue(post.getCalculatedValue())
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

  private String summariseBody(String body) {
    return body.lines()
      .limit(MAX_SUMMARY_BODY_LINES)
      .collect(Collectors.joining("\n"));
  }
}
