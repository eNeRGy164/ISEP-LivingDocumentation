package com.infosupport.ldoc.analyzerj.descriptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MemberDescription(
    @JsonProperty("Name")
    String name,

    @JsonProperty("Attributes")
    @JsonInclude(Include.NON_EMPTY)
    List<Description> attributes
) implements Description {

  public MemberDescription(String name) {
    this(name, List.of());
  }
}