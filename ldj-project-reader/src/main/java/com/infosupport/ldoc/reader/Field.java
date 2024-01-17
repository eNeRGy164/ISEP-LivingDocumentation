package com.infosupport.ldoc.reader;

/**
 * Interface for class of useful methods when dealing with Field nodes.
 */
public interface Field extends HasName, HasType, HasModifiers, HasComment, Node {

  String initializer();
}
