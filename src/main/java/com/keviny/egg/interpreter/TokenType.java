package com.keviny.egg.interpreter;

// This enum represents the different tokens present in Egg
public enum TokenType {
  NUMBER,

  // Intrinsic methods to represent pen drawing
  // Only one pen can exist at a time
  PEN_DOWN,
  PEN_UP,
  PEN_COLOR,
  PEN_MOVE,
  PEN_ROTATE,

  RED,
  ORANGE,
  YELLOW,
  GREEN,
  BLUE,
  PURPLE,
  BLACK,

  // Means end of file
  EOF;
}
