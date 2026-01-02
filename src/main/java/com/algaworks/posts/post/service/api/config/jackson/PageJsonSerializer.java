package com.algaworks.posts.post.service.api.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {
  @Override
  public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    gen.writeNumberField("page", page.getNumber());
    gen.writeNumberField("size", page.getSize());
    gen.writeNumberField("totalPages", page.getTotalPages());
    gen.writeNumberField("totalElements", page.getTotalElements());
    gen.writeObjectField("content", page.getContent());
    gen.writeEndObject();
  }
}
