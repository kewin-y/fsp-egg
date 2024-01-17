package com.keviny.egg.interpreter;

public class EggError {

  public EggError(int line, String message) {
    this.line = line;
    this.message = message;
  }

  private int line;
  private String message;

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Error on line " + line + ": " + message;
  }
}
