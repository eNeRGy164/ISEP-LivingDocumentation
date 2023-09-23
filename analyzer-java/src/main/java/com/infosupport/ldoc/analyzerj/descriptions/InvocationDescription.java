package com.infosupport.ldoc.analyzerj.descriptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record InvocationDescription(
    @JsonProperty("ContainingType")
    String containingType,

    @JsonProperty("Name")
    String name,

    @JsonProperty("Arguments")
    @JsonInclude(Include.NON_EMPTY)
    List<Description> arguments
) implements Description {

  @JsonProperty("$type")
  public String getType() {
    return "LivingDocumentation.InvocationDescription, LivingDocumentation.Descriptions";
  }
}
