package com.keviny.egg.interpreter;

// An error for when there is no script selected
public class NoScriptError extends EggError {
  public NoScriptError(int line, String message, String what) {
    super(line, message, what);
  }

  // Overloaded constructor so no arguments needed
  public NoScriptError() {
    super(-1, "", "");
  }

  @Override
  public String toString() {
    return "No Script Selected!";
  }
}
