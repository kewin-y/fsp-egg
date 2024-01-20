package com.keviny.egg.interpreter;

// This class represents a token
// A simple construct that the computer can work with to run the language
// No significant methods
// Source: https://craftinginterpreters.com/
public class Token {
  private final TokenType type;
  private final String lexeme; // Raw substring that has actual meaning
  private final Object literal; // The value associated with the token

  private final int line;

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

  public TokenType getType() {
    return type;
  }

  public String getLexeme() {
    return lexeme;
  }

  public Object getLiteral() {
    return literal;
  }

  public int getLine() {
    return line;
  }
}
