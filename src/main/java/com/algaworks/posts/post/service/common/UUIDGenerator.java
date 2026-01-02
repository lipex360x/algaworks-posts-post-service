package com.algaworks.posts.post.service.common;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

import java.util.UUID;

public class UUIDGenerator {
  private static final TimeBasedEpochRandomGenerator timeBasedEpochRandomGenerator =
    Generators.timeBasedEpochRandomGenerator();

  private UUIDGenerator() {}

  public static UUID generate() {
    return timeBasedEpochRandomGenerator.generate();
  }
}
