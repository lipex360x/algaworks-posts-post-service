package com.algaworks.posts.post.service.domain.service;

import com.algaworks.posts.post.service.domain.entity.Post;
import com.algaworks.posts.post.service.domain.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public void create(Post post) {
    postRepository.saveAndFlush(post);
    log.info("send to queue");
  }

}
