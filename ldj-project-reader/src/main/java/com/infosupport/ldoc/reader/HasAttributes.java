package com.infosupport.ldoc.reader;

import java.util.stream.Stream;

public interface HasAttributes {

  Stream<Attribute> attributes();

  default Stream<Attribute> attributesOfType(String fullNameOfType) {
    return attributes().filter(attr -> attr.type().equals(fullNameOfType));
  }

  default boolean hasAttribute(String fullNameOfType) {
    return attributes().anyMatch(attr -> attr.type().equals(fullNameOfType));
  }
}