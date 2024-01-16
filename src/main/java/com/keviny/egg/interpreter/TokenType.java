package com.keviny.egg.interpreter;

public enum TokenType {
  // Single character
  LEFT_PAREN,
  RIGHT_PAREN,

  COMMA,
  MINUS,
  PLUS,
  SLASH,
  STAR,
  SEMICOLON,

  LESS,
  LESS_EQUAL,

  NUMBER,

  // Intrinsic methods to represent pen drawing
  // Only one pen can exist at a time
  PEN_DOWN,
  PEN_UP,
  PEN_COLOR,
  PEN_GO,
  PEN_ROTATE,

  EOF
}
