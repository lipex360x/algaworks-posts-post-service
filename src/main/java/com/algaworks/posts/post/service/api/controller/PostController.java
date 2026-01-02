package com.algaworks.posts.post.service.api.controller;

import com.algaworks.posts.post.service.api.assembler.PostAssembler;
import com.algaworks.posts.post.service.api.exception.InvalidRequestException;
import com.algaworks.posts.post.service.api.helper.ResourceUriHelper;
import com.algaworks.posts.post.service.api.model.PostInput;
import com.algaworks.posts.post.service.api.model.PostSummaryOutput;
import com.algaworks.posts.post.service.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
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
    validateInput(input);
    Post post = postAssembler.toEntity(input);
    ResourceUriHelper.addUriInResponseHeader(post.getId());
    return postAssembler.toSummaryModel(post);
  }

  private void validateInput(PostInput input) {
    if (StringUtils.isNotBlank(input.getAuthor())) throw new InvalidRequestException("author");
    if (StringUtils.isNotBlank(input.getTitle())) throw new InvalidRequestException("title");
    if (StringUtils.isBlank(input.getBody())) throw new InvalidRequestException("body");
  }

}
