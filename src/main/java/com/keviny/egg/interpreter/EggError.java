package com.keviny.egg.interpreter;

public class EggError {
  private int line;
  private String message;
  private String what;

  public EggError(int line, String message, String what) {
    this.line = line;
    this.message = message;
    this.what = what;
  }

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
    return "Error on line " + line + ": " + message + ": " + "\"" + what + "\"";
  }
}
