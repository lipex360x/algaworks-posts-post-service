package com.algaworks.posts.post.service.domain.service;

import com.algaworks.posts.post.service.domain.dto.ResultPostCostDTO;
import com.algaworks.posts.post.service.domain.entity.Post;
import com.algaworks.posts.post.service.domain.exception.EntityNotFoundException;
import com.algaworks.posts.post.service.domain.repository.PostRepository;
import com.algaworks.posts.post.service.domain.dto.CalculatePostCostDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.algaworks.posts.post.service.infra.rabbitmq.RabbitMQConfig.DIRECT_EXCHANGE_POST_PROCESSING;
import static com.algaworks.posts.post.service.infra.rabbitmq.RabbitMQConfig.ROUTING_KEY_POST_PROCESSING;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

  private final PostRepository postRepository;
  private final RabbitTemplate rabbitTemplate;

  @Transactional
  public void create(Post post) {
    postRepository.saveAndFlush(post);
    sendPostToProcessCost(post);
  }

  public Post findOrFail(UUID postId) {
    return postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException(postId));
  }

  private void sendPostToProcessCost(Post post) {
    Object payload = CalculatePostCostDTO.builder()
      .postId(post.getId().toString())
      .postBody(post.getBody())
      .build();
    MessagePostProcessor messagePostProcessor = message -> {
      message.getMessageProperties().setHeader("postId", post.getId());
      return message;
    };
    rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_POST_PROCESSING, ROUTING_KEY_POST_PROCESSING, payload, messagePostProcessor);
    log.info("post id {} sent to calculate cost", post.getId());
  }

  @Transactional
  public void updatePostCost(ResultPostCostDTO postCalculated) {
    Post post = findOrFail(postCalculated.getPostId());
    post.updatePostCost(postCalculated);
    postRepository.saveAndFlush(post);
    log.info("post id {} updated with cost", postCalculated.getPostId());
  }

}
