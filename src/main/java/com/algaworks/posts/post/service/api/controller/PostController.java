package com.algaworks.posts.post.service.api.controller;

import com.algaworks.posts.post.service.api.assembler.PostAssembler;
import com.algaworks.posts.post.service.api.config.data.PageableTranslator;
import com.algaworks.posts.post.service.api.exception.InvalidRequestException;
import com.algaworks.posts.post.service.api.helper.ResourceUriHelper;
import com.algaworks.posts.post.service.api.model.PostInput;
import com.algaworks.posts.post.service.api.model.PostOutput;
import com.algaworks.posts.post.service.api.model.PostSummaryOutput;
import com.algaworks.posts.post.service.domain.entity.Post;
import com.algaworks.posts.post.service.domain.repository.PostRepository;
import com.algaworks.posts.post.service.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

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
    pageable = translatePageable(pageable);
    Page<Post> posts = postRepository.findAll(pageable);
    return posts.map(postAssembler::toSummaryModel);
  }

  @GetMapping("/{postId}")
  public PostOutput findOne(@PathVariable UUID postId) {
    Post post = postService.findOrFail(postId);
    return postAssembler.toModel(post);
  }

  private void validateInput(PostInput input) {
    if (StringUtils.isBlank(input.getAuthor())) throw new InvalidRequestException("author");
    if (StringUtils.isBlank(input.getTitle())) throw new InvalidRequestException("title");
    if (StringUtils.isBlank(input.getBody())) throw new InvalidRequestException("body");
  }

  private Pageable translatePageable(Pageable apiPageable) {
    var fieldMap = Map.of(
      "summary", "body",
      "body", "body",
      "createdAt", "createdAt",
      "updatedAt", "updatedAt"
    );
    return PageableTranslator.translate(apiPageable, fieldMap);
  }

}
