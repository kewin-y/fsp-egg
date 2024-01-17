package com.keviny.egg.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EggScanner {
  private final String source;
  private final List<Token> tokens = new ArrayList<>();

  private int start = 0; // First character of lexeme
  private int current = 0; // Current character of lexeme
  private int line = 1;

  private static final Map<String, TokenType> keywords;

  static {
    keywords = new HashMap<>();
    // Graphics:
    keywords.put("pen_down", TokenType.PEN_DOWN);
    keywords.put("pen_up", TokenType.PEN_UP);
    keywords.put("pen_color", TokenType.PEN_COLOR);
    keywords.put("pen_move", TokenType.PEN_MOVE);
    keywords.put("pen_rotate", TokenType.PEN_ROTATE);
    
    // Colors:
    keywords.put("RED", TokenType.RED);
    keywords.put("ORANGE", TokenType.ORANGE);
    keywords.put("YELLOW", TokenType.YELLOW);
    keywords.put("GREEN", TokenType.GREEN);
    keywords.put("BLUE", TokenType.BLUE);
    keywords.put("PURPLE", TokenType.PURPLE);
  }

  public EggScanner(String source) {
    this.source = source;
  }

  private boolean isAtEnd() {
    return current >= source.length();
  }

  public List<Token> scanTokens() {
    while (!isAtEnd()) {
      start = current;
      scanToken(); // Increases current by an arbitrary amount until a valid token is found
    }

    tokens.add(new Token(TokenType.EOF, "", null, line));
    return tokens;
  }

  private void scanToken() {
    // Gets the urrent character then imediately goes to the next
    char c = advance();
    switch (c) {
      case '/':
        if (matchNext('/')) {
          // Continue reading until the end of line or file when two slashes (comment) are
          // read & do nothing
          while (peek() != '\n' && !isAtEnd()) advance();
        }
        break;
      case ' ': // Useless characters since anguage won't be whitespace dependent
      case '\r':
      case '\t':
        break;
      case '\n':
        line++;
        break;

      default:
        if (isDigit(c)) {
          addNumber();

        } else if (isAlpha(c)) {
          if (!addIdentifierOrKeyword()) {
            EggInterpreter.error(line, "Unexpected Token!");
          }
        } else {
          EggInterpreter.error(line, "Unexpected Character");
          break;
        }
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

  private char peek() {
    // Checks the next character
    // Called "lookahead"
    if (isAtEnd()) return '\0';
    return source.charAt(current);
  }

  private char peekNext() {
    if (current + 1 >= source.length()) return '\0';
    return source.charAt(current + 1);
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  private boolean isAlpha(char c) {
    return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '_'));
  }

  private boolean isAlphaNumeric(char c) {
    return isDigit(c) || isAlpha(c);
  }

  private void addNumber() {
    while (isDigit(peek())) advance();

    if (peek() == '.' && isDigit(peekNext())) {
      // Include the decimal
      advance();
      while (isDigit(peek())) advance();
    }

    addToken(TokenType.NUMBER, Double.parseDouble(source.substring(start, current)));
  }

  private boolean addIdentifierOrKeyword() {
    while (isAlphaNumeric(peek())) advance();

    String text = source.substring(start, current);
    TokenType type = keywords.get(text);
    if (type == null) {
      return false;
    }
    addToken(type);
    return true;
  }
}
