package com.algaworks.posts.post.service.infra.rabbitmq;

import com.algaworks.posts.post.service.domain.dto.CalculatePostCostDTO;
import com.algaworks.posts.post.service.domain.dto.ResultPostCostDTO;
import com.algaworks.posts.post.service.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.algaworks.posts.post.service.infra.rabbitmq.RabbitMQConfig.QUEUE_POST_PROCESSING_RESULT;


@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

  private final PostService postService;

  @RabbitListener(queues = QUEUE_POST_PROCESSING_RESULT, concurrency = "2-5")
  public void handle(
    @Payload ResultPostCostDTO resultPostCostDTO,
    @Headers Map<String, Object> headers
  ) {
    postService.updatePostCost(resultPostCostDTO);
  }
}
