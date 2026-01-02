package com.algaworks.posts.post.service.api.controller;

import com.algaworks.posts.post.service.api.mapper.PostAssembler;
import com.algaworks.posts.post.service.api.model.PostInput;
import com.algaworks.posts.post.service.api.model.PostSummaryOutput;
import com.algaworks.posts.post.service.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostAssembler postAssembler;

  @PostMapping
  public PostSummaryOutput create(@RequestBody PostInput input) {
    Post post = postAssembler.toEntity(input);
    return postAssembler.toSummaryModel(post);
  }

}
