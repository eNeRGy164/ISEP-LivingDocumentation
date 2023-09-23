package com.infosupport.ldoc.analyzerj.descriptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record SwitchDescription(
    @JsonProperty("Expression")
    String expression,

    @JsonProperty("Sections")
    @JsonInclude(Include.NON_EMPTY)
    List<Description> sections
) implements Description {

  @JsonProperty("$type")
  public String getType() {
    return "LivingDocumentation.Switch, LivingDocumentation.Statements";
  }
}
