package com.keviny.egg.interpreter;

import java.util.HashMap;
import java.util.Map;

// This class the scanner (lexer) of the small languageo of Egg
public class EggScanner {
  private final String source;
  private final TokensWrapper tokensWrapper = new TokensWrapper();

  private int lexemeStart = 0; // First character of lexeme -> mutable state variable
  private int lexemeCurrent = 0; // Current character of lexeme -> mutable state variable
  private int currentLine = 1;

  private static final Map<String, TokenType> KEYWORDS;

  // Keywords and the tokens associated with them
  static {
    KEYWORDS = new HashMap<>();
    // Graphics:
    KEYWORDS.put("pen_down", TokenType.PEN_DOWN);
    KEYWORDS.put("pen_up", TokenType.PEN_UP);
    KEYWORDS.put("pen_color", TokenType.PEN_COLOR);
    KEYWORDS.put("pen_move", TokenType.PEN_MOVE);
    KEYWORDS.put("pen_rotate", TokenType.PEN_ROTATE);

    // Colors:
    KEYWORDS.put("red", TokenType.RED);
    KEYWORDS.put("orange", TokenType.ORANGE);
    KEYWORDS.put("yellow", TokenType.YELLOW);
    KEYWORDS.put("green", TokenType.GREEN);
    KEYWORDS.put("blue", TokenType.BLUE);
    KEYWORDS.put("purple", TokenType.PURPLE);
  }

  // Constructor
  public EggScanner(String source) {
    this.source = source;
  }

  // Wether or not the scanner has reached the end.
  private boolean isAtEnd() {
    return lexemeCurrent >= source.length();
  }

  // Scans the entire file
  public TokensWrapper scanTokens() {
    while (!isAtEnd()) {
      lexemeStart = lexemeCurrent;
      scanToken(); // Increases current by an arbitrary amount until a valid token is found
    }

    tokensWrapper.getTokens().add(new Token(TokenType.EOF, "", null, currentLine));
    return tokensWrapper;
  }

  // Scans and matches a single token
  private void scanToken() {
    // Gets the urrent character then imediately goes to the next

    char c = advance();

    // Switch statement to determine tokens
    switch (c) {
      case '/':
        if (matchNext('/')) {
          // Continue reading until the end of line or file when two slashes (comment) are
          // read & do nothing
          while (peek() != '\n' && !isAtEnd()) advance();
        }
        break;
      case ' ': // Useless characters since language won't be whitespace dependent (Although
        // whitespace is required to differentiate token types)
      case '\r':
      case '\t':
        break;
      case '\n':
        currentLine++;
        break;
      default:
        if (isDigit(c)) {
          addNumber();
        } else if (isAlpha(c)) {
          if (!addIdentifierOrKeyword()) {
            tokensWrapper
                .getErrors()
                .add(
                    new EggError(
                        currentLine,
                        "Unrecognized Token",
                        source.substring(lexemeStart, lexemeCurrent)));
          }
        } else {
          tokensWrapper
              .getErrors()
              .add(
                  new EggError(
                      currentLine,
                      "Unrecognized Character",
                      "" + source.charAt(lexemeCurrent - 1)));
          break;
        }
    }
  }

  // Return the current character, then increment it
  private char advance() {
    return source.charAt(lexemeCurrent++);
  }

  // Overloaded method in case the token has no literal
  private void addToken(TokenType tokenType) {
    addToken(tokenType, null);
  }

  // Adds a token to the field
  private void addToken(TokenType tokenType, Object literal) {
    String lexeme = source.substring(lexemeStart, lexemeCurrent);
    tokensWrapper.getTokens().add(new Token(tokenType, lexeme, literal, currentLine));
  }

  /**
   * Increments the current character count and determines whether or not it matches with an
   * expected character Useful for scanning 2-character long tokens Only used for comments in this
   * language
   *
   * @param expected The character to match
   */
  private boolean matchNext(char expected) {
    if (isAtEnd()) return false;
    if (source.charAt(lexemeCurrent) != expected) return false;

    lexemeCurrent++;
    return true;
  }

  // Checks the current character
  // Called "lookahead"
  private char peek() {
    if (isAtEnd()) return '\0';
    return source.charAt(lexemeCurrent);
  }

  // Checks the next character
  private char peekNext() {
    if (lexemeCurrent + 1 >= source.length()) return '\0';
    return source.charAt(lexemeCurrent + 1);
  }

  /**
   * Method to check whether or not a character is a number Since java's builtin method checks for
   * strange (non-arabic) digits
   * @param c the character to check
   */
  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  // Check if a character is a letter
  private boolean isAlpha(char c) {
    return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '_'));
  }

  // Checks if a character is a letter or a number
  private boolean isAlphaNumeric(char c) {
    return isDigit(c) || isAlpha(c);
  }

  // Adds a number token to the tokenWrapper token's field
  private void addNumber() {
    while (isDigit(peek())) advance();

    if (peek() == '.' && isDigit(peekNext())) {
      // Include the decimal
      advance();
      while (isDigit(peek())) advance();
    }

    addToken(TokenType.NUMBER, Float.parseFloat(source.substring(lexemeStart, lexemeCurrent)));
  }

  // Adds a keyword token to the tokenWrapper's tokens field
  // Advances through souce and returns false if an invalid identifier is found
  // Returns true if
  private boolean addIdentifierOrKeyword() {
    while (isAlphaNumeric(peek())) advance();

    String text = source.substring(lexemeStart, lexemeCurrent);
    TokenType type = KEYWORDS.get(text);
    if (type == null) {
      return false;
    }
    addToken(type);
    return true;
  }
}
