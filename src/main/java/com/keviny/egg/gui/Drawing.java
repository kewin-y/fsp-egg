package com.keviny.egg.gui;

import com.keviny.egg.interpreter.EggError;
import com.keviny.egg.interpreter.EggScanner;
import com.keviny.egg.interpreter.Token;
import com.keviny.egg.interpreter.TokenType;
import com.raylib.Jaylib;
import com.raylib.Raylib;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

// This class is a representation of the user's script (Drawing)
public class Drawing {
  private File script;

  private List<Token> tokens;
  private List<EggError> scanningErrors;

  private int startToken;
  private int endToken;

  private Raylib.Color color = Jaylib.BLACK;

  // Executes the script and draws the image
  public void draw() {
    if (tokens == null || script == null) {
      // TODO: Implement error
      System.out.println("No script to draw!");
      return;
    }
  }

  private void executeScript() {}

  private void addInstruction() {}

  private boolean isAtEnd() {
    return tokens.get(endToken).getType() == TokenType.EOF;
  }

  // Method that dispays an error
  public void error(int line, String message) {}

  private void report(int line, String where, String message) {}

  public File getScript() {
    return script;
  }

  // Special setter for the script which also sets the tokens
  public void setScript(File script) {
    this.script = script;
    try {
      setTokens();
    } catch (Exception e) {
      System.err.println("Could not set script due to: " + e.getMessage());
    }
  }

  private void setTokens() throws IOException {
    FileInputStream fis = new FileInputStream(script);
    long size = script.length();

    byte[] byteArray = new byte[(int) size];
    fis.read(byteArray);

    fis.close();

    EggScanner scanner = new EggScanner(new String(byteArray));
    var tw = scanner.scanTokens();

    tokens = tw.getTokens();
    scanningErrors = tw.getErrors();

    for (Token token : tokens) {
      System.out.println(token);
    }
  }
}
