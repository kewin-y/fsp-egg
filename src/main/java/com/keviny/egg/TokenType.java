package com.keviny.egg;

public enum TokenType {
  // Single character
  LEFT_PAREN,
  RIGHT_PAREN,

  LEFT_BRACE,
  RIGHT_BRACE,

  COMMA,
  PERIOD,
  MINUS,
  PLUS,
  SEMICOLON,
  SLASH,
  STAR,

  // One or two character tokens
  BANG,
  BANG_EQUAL,

  EQUAL,
  EQUAL_EQUAL,

  GREATER,
  GREATER_EQUAL,

  LESS,
  LESS_EQUAL,

  // Literals
  IDENTIFIER,
  STRING,
  // By default, all numbers are represented by floating point
  // Represents ints, doubles etc.
  NUMBER,

  // Keywords
  // Consider removing class and other unecessary features?
  AND,
  OR,
  CLASS,
  FUN,
  VAR,

  FOR,
  REPEAT,
  WHILE,

  IF,
  ELSE,

  TRUE,
  FALSE,

  NOTHING, // The language's null type since nothing is easier to conceptualize
  PRINT,
  RETURN,
  SUPER,
  THIS,

  // Intrinsic methods to represent pen drawing
  // Only one pen can exist at a time
  PEN_DOWN,
  PEN_UP,
  PEN_COLOR,
  PEN_GO,
  PEN_ROTATE,
  // TODO: Hard stuff
  // PEN_MOVE -> linearly interpolated (might be hard)
  // PEN_SPEED -> this might be hard

  EOF
}
