package com.algaworks.posts.post.service.api.controller;

import com.algaworks.posts.post.service.api.assembler.PostAssembler;
import com.algaworks.posts.post.service.api.exception.InvalidRequestException;
import com.algaworks.posts.post.service.api.helper.ResourceUriHelper;
import com.algaworks.posts.post.service.api.model.PostInput;
import com.algaworks.posts.post.service.api.model.PostSummaryOutput;
import com.algaworks.posts.post.service.domain.entity.Post;
import com.algaworks.posts.post.service.domain.repository.PostRepository;
import com.algaworks.posts.post.service.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostAssembler postAssembler;
  private final PostRepository postRepository;
  private final PostService postService;

  @PostMapping
  public PostSummaryOutput create(@RequestBody PostInput input) {
    validateInput(input);
    Post post = postAssembler.toEntity(input);
    postService.create(post);
    ResourceUriHelper.addUriInResponseHeader(post.getId());
    return postAssembler.toSummaryModel(post);
  }

  @GetMapping
  public Page<PostSummaryOutput> findAll(
    @PageableDefault Pageable pageable
  ) {
    Page<Post> posts = postRepository.findAll(pageable);
    return posts.map(postAssembler::toSummaryModel);
  }

  private void validateInput(PostInput input) {
    if (StringUtils.isBlank(input.getAuthor())) throw new InvalidRequestException("author");
    if (StringUtils.isBlank(input.getTitle())) throw new InvalidRequestException("title");
    if (StringUtils.isBlank(input.getBody())) throw new InvalidRequestException("body");
  }

}
