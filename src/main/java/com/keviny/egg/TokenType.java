package com.keviny.egg;

public enum TokenType {
  // Single character
  LEFT_PAREN, // (
  RIGHT_PAREN, // )

  LEFT_BRACE, // {
  RIGHT_BRACE, // }

  // LEFT_BRACKET, // [
  // RIGHT_BRACKET, // ]

  COMMA,
  DOT, // .
  MINUS,
  PLUS,
  SEMICOLON,
  SLASH,
  STAR,

  // One or two character tokens
  BANG, // !
  BANG_EQUAL, // !=

  GREATER, // >
  GREATER_EQUAL, // >=

  LESS, // <
  LESS_EQUAL, // <=

  // Literals
  IDENTIFIER,
  STRING,
  // By default, all numbers are represented by floating point
  // Represents ints, doubles etc.
  NUMBER,

  // Keywords
  AND,
  CLASS,
  ELSE,
  FALSE,
  FUN,
  FOR,
  IF,
  NIL,
  PR,
  PRINT,
  RETURN,
  SUPER,
  THIS,
  TRUE,
  VAR,
  WHILE,

  // Graphics
  // DRAW, 

  EOF
}
