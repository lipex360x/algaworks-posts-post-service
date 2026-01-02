package com.algaworks.posts.post.service.domain.repository;

import com.algaworks.posts.post.service.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}