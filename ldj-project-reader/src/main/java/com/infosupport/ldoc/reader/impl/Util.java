package com.infosupport.ldoc.reader.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.infosupport.ldoc.reader.Statement;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Util {

  static <T> Stream<T> streamOf(JsonNode node, Function<JsonNode, T> converter) {
    assert node.isContainerNode();

    return StreamSupport.stream(node.spliterator(), false).map(converter);
  }

  static Stream<Statement> statements(ProjectImpl project, JsonNode node) {
    assert node.isObject();
    assert node.has("Statements");

    return streamOf(node.path("Statements"), s -> {
      return switch (s.path("$type").textValue().split(", ")[0]) {
        case "LivingDocumentation.AssignmentDescription" -> new AssignmentImpl(node);
        case "LivingDocumentation.ForEach" -> new ForEachImpl(project, node);
        case "LivingDocumentation.If" -> new IfImpl(project, node);
        case "LivingDocumentation.InvocationDescription" -> new InvocationImpl(project, node);
        case "LivingDocumentation.ReturnDescription" -> new ReturnImpl(node);
        case "LivingDocumentation.Switch" -> new SwitchImpl(project, node);
        default -> new UnknownStatementImpl();
      };
    });
  }
}
