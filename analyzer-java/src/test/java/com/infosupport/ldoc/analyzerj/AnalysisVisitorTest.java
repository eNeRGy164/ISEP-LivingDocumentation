package com.infosupport.ldoc.analyzerj;

import static org.junit.jupiter.api.Assertions.*;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.infosupport.ldoc.analyzerj.descriptions.Description;
import com.infosupport.ldoc.analyzerj.descriptions.TypeDescription;
import com.infosupport.ldoc.analyzerj.descriptions.TypeType;
import java.util.List;
import org.junit.jupiter.api.Test;

class AnalysisVisitorTest {

  private final JavaParser parser;
  private final SymbolResolver solver;

  AnalysisVisitorTest() {
    solver = new JavaSymbolSolver(new ReflectionTypeSolver());
    parser = new JavaParser(new ParserConfiguration().setSymbolResolver(solver));
  }

  private List<Description> parse(String code) {
    return parser.parse(code).getResult().orElseThrow().accept(new AnalysisVisitor(solver), null);
  }

  @Test
  void type_description_for_class() {
    assertIterableEquals(
        List.of(new TypeDescription(TypeType.CLASS, "Foo", List.of())),
        parse("class Foo {}"));

    assertIterableEquals(
        List.of(new TypeDescription(TypeType.CLASS, "some.example.Bar", List.of())),
        parse("package some.example; class Bar {}"));

    assertIterableEquals(
        List.of(new TypeDescription(TypeType.CLASS, "Baz", List.of("java.lang.Object"))),
        parse("class Baz extends Object {}"));

    assertIterableEquals(
        List.of(new TypeDescription(TypeType.CLASS, "Baz", List.of("java.io.Serializable"))),
        parse("import java.io.Serializable; class Baz implements Serializable {}"));
  }

  @Test
  void type_description_for_interface() {
    assertIterableEquals(
        List.of(new TypeDescription(TypeType.INTERFACE, "Oogle", List.of())),
        parse("interface Oogle {}"));

    assertIterableEquals(
        List.of(new TypeDescription(TypeType.INTERFACE, "some.example.Foogle", List.of())),
        parse("package some.example; interface Foogle {}"));

    assertIterableEquals(
        List.of(new TypeDescription(TypeType.INTERFACE, "Boogle", List.of("java.lang.Comparable"))),
        parse("interface Boogle extends Comparable {}"));
  }

  @Test
  void type_description_for_record() {
    assertIterableEquals(
        List.of(new TypeDescription(TypeType.STRUCT, "Blarg", List.of("java.lang.Runnable"))),
        parse("record Blarg() implements Runnable {}"));
  }

  @Test
  void type_description_for_enum() {
    assertIterableEquals(
        List.of(new TypeDescription(TypeType.ENUM, "Pippo", List.of("java.lang.Cloneable"))),
        parse("enum Pippo implements Cloneable {}"));
  }
}
