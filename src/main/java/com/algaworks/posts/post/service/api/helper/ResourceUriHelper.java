package com.algaworks.posts.post.service.api.helper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@UtilityClass
public class ResourceUriHelper {

  public static void addUriInResponseHeader(Object resourceId) {
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
      .path("/{id}")
      .buildAndExpand(resourceId)
      .toUri();
    RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
    if (!(attrs instanceof ServletRequestAttributes servletAttrs)) return;
    HttpServletResponse response = servletAttrs.getResponse();
    if (response == null) return;
    response.setHeader(HttpHeaders.LOCATION, uri.toString());
  }

}
