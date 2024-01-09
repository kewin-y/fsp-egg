package com.keviny.egg;

import java.util.ArrayList;
import java.util.List;

public class EggScanner {
  private final String source;
  private final List<Token> tokens = new ArrayList<>();

  private int start = 0; // First character of lexeme
  private int current = 0; // Current character of lexeme
  private int line = 1;

  public EggScanner(String source) {
    this.source = source;
  }

  private boolean isAtEnd() {
    return current >= source.length();
    // أنا جوعان
  }

  public List<Token> scanTokens() {
    while (!isAtEnd()) {
      start = current;
      scanToken(); // Increases current by an arbitrary amount unti a valid token is found
    }

    tokens.add(new Token(TokenType.EOF, "", null, line));
    return tokens;
  }

  private void scanToken() {
    // Gets the urrent character then imediately goes to the next
    char c = advance();
    switch (c) {
      case '(':
        addToken(TokenType.LEFT_PAREN);
        break;
      case ')':
        addToken(TokenType.RIGHT_PAREN);
        break;
      case '{':
        addToken(TokenType.LEFT_BRACE);
        break;
      case '}':
        addToken(TokenType.RIGHT_BRACE);
        break;
      case ',':
        addToken(TokenType.COMMA);
        break;
      case '.':
        addToken(TokenType.PERIOD);
        break;
      case '-':
        addToken(TokenType.MINUS);
        break;
      case '+':
        addToken(TokenType.PLUS);
        break;
      case ';':
        addToken(TokenType.SEMICOLON);
        break;
      case '*':
        addToken(TokenType.STAR);
        break;
      case '!':
        addToken(matchNext('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
        break;
      case '=':
        addToken(matchNext('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
        break;
      case '<':
        addToken(matchNext('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
        break;
      case '>':
        addToken(matchNext('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
        break;
      default:
        Egg.error(line, "Unexpected Character");
        break;
    }
  }

  private char advance() {
    // Return current, then increment it 
    return source.charAt(current++);
  }

  private void addToken(TokenType tokenType) {
    addToken(tokenType, null);
  }

  private void addToken(TokenType tokenType, Object literal) {
    String lexeme = source.substring(start, current);
    tokens.add(new Token(tokenType, lexeme, literal, line));
  }

  private boolean matchNext(char expected) {
    if (isAtEnd()) return false;
    if (source.charAt(current) != expected) return false;

    current++;
    return true;
  }
}
