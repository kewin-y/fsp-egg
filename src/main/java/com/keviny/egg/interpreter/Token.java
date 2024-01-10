package com.keviny.egg.interpreter;

public class Token {
  protected final TokenType type;
  protected final String lexeme; // Raw substring that has actual meaning
  protected final Object literal;
  protected final int line;

  public Token(TokenType type, String lexeme, Object literal, int line) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
  }

  @Override
  public String toString() {
    return type + " " + lexeme + " " + literal;
  }
}
