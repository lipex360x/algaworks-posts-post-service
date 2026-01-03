package com.algaworks.posts.post.service.infra.rabbitmq.listener;

import com.algaworks.posts.post.service.domain.dto.CalculatePostCostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Map;

import static com.algaworks.posts.post.service.infra.rabbitmq.config.RabbitMQConfig.QUEUE_POST_PROCESSING;
import static com.algaworks.posts.post.service.infra.rabbitmq.config.RabbitMQConfig.QUEUE_POST_PROCESSING_RESULT;


@RabbitListener(queues = QUEUE_POST_PROCESSING, concurrency = "2-5")
@Slf4j
public class PostProcessingResultListener {

  public void handle(
    @Payload CalculatePostCostDTO calculatePostCostDTO,
    @Headers Map<String, Object> headers
  ) {
    log.info(calculatePostCostDTO.toString());
    log.info(headers.toString());
  }
}
