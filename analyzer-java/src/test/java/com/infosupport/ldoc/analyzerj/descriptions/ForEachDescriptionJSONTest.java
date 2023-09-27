package com.infosupport.ldoc.analyzerj.descriptions;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class ForEachDescriptionJSONTest {

  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  void foreach_description_serializes_as_expected() throws IOException {
    String expected = """
        {
          "$type": "LivingDocumentation.ForEach, LivingDocumentation.Statements",
          "Expression": "String piece : pieces",
          "Statements": [
            {
              "$type": "LivingDocumentation.ReturnDescription, LivingDocumentation.Descriptions"
            }
          ]
        }
        """;

    assertEquals(
        mapper.readTree(expected),
        mapper.valueToTree(
            new ForEachDescription("String piece : pieces", List.of(new ReturnDescription()))));
  }
}
