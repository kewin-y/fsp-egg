package com.keviny.egg.interpreter;

// Class to rerpresent an error in the script
public class EggError {
  private int line;
  private String message;
  private String what;

  public EggError(int line, String message, String what) {
    this.line = line;
    this.message = message;
    this.what = what;
  }

  // Getters & setters
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

  // Modified toString() method to format the error
  @Override
  public String toString() {
    return "Error on line " + line + ": " + message + ": " + "\"" + what + "\"";
  }
}
