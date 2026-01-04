package com.algaworks.posts.post.service.domain.entity;

import com.algaworks.posts.post.service.domain.dto.ResultPostCostDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Post {

  @Id
  private UUID id;
  private String author;
  private String title;
  private String body;
  private Integer wordCount;
  private BigDecimal calculatedValue;

  @CreationTimestamp
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  private OffsetDateTime updatedAt;

  public void updatePostCost(ResultPostCostDTO resultPostCostDTO) {
    setWordCount(resultPostCostDTO.getWordCount());
    setCalculatedValue(resultPostCostDTO.getCalculatedValue());
  }

}
