package com.algaworks.posts.post.service.api.config.data;

import com.algaworks.posts.post.service.domain.exception.InvalidFilterPropertyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class PageableTranslator {
  private PageableTranslator() {}

  public static Pageable translate(Pageable pageable, Map<String, String> mapping) {
    var orders = pageable.getSort().stream()
      .map(order -> {
        var property = mapping.get(order.getProperty());
        if (property == null) throw new InvalidFilterPropertyException(order.getProperty());
        return new Sort.Order(order.getDirection(), property);
      })
      .toList();
    return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
  }
}

