package com.keviny.egg.interpreter;

// This enum represents the different tokens present in Egg
public enum TokenType {
  // Single character
  LEFT_PAREN,
  RIGHT_PAREN,
  COMMA,

  NUMBER,
  // Intrinsic methods to represent pen drawing
  // Only one pen can exist at a time
  PEN_DOWN,
  PEN_UP,
  PEN_COLOR,
  PEN_GO,
  PEN_ROTATE,

  // Colors
  RED,
  ORANGE,
  YELLOW,
  GREEN,
  BLUE,
  PURPLE,
  
  // Means end of file
  EOF
}
