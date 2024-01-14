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
    keywords.put("and", TokenType.AND);
    keywords.put("class", TokenType.CLASS);
    keywords.put("else", TokenType.ELSE);
    keywords.put("false", TokenType.FALSE);
    keywords.put("for", TokenType.FOR);
    keywords.put("fun", TokenType.FUN);
    keywords.put("if", TokenType.IF);
    keywords.put("nothing", TokenType.NOTHING);
    keywords.put("or", TokenType.OR);
    keywords.put("print", TokenType.PRINT);
    keywords.put("return", TokenType.RETURN);
    keywords.put("super", TokenType.SUPER);
    keywords.put("this", TokenType.THIS);
    keywords.put("true", TokenType.TRUE);
    keywords.put("var", TokenType.VAR);
    keywords.put("while", TokenType.WHILE);

    // Graphics:
    keywords.put("pen_down", TokenType.PEN_DOWN);
    keywords.put("pen_up", TokenType.PEN_UP);
    keywords.put("pen_color", TokenType.PEN_COLOR);
    keywords.put("pen_go", TokenType.PEN_GO);
    keywords.put("pen_rotate", TokenType.PEN_ROTATE);
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
      case '/':
        if (matchNext('/')) {
          // Continue reading until the end of line or file when two slashes (comment) are
          // read & do nothing
          while (peek() != '\n' && !isAtEnd()) advance();
        } else {
          addToken(TokenType.SLASH);
        }
        break;
      case ' ': // Useless characters since anguage won't be whitespace dependent
      case '\r':
      case '\t':
        break;
      case '\n':
        line++;
        break;
      case '"':
        addString();
        break;

      default:
        if (isDigit(c)) {
          addNumber();

        } else if (isAlpha(c)) {
          addIdentifierOrKeyword();
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

  private void addString() {
    while (peek() != '"' && !isAtEnd()) {
      if (peek() == '\n') line++;
      advance();
    }

    if (isAtEnd()) {
      EggInterpreter.error(line, "Unterminated String");
    }

    // Still want to advance over the closing " even though it gets by the substring
    // method call
    // This is because it is necessary to consider the lexeme (which could be "foo")
    advance();
    String literal = source.substring(start + 1, current - 1);
    addToken(TokenType.STRING, literal);
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

  private void addIdentifierOrKeyword() {
    while (isAlphaNumeric(peek())) advance();

    String text = source.substring(start, current);
    TokenType type = keywords.get(text);
    if (type == null) type = TokenType.IDENTIFIER;
    addToken(type);
  }
}
