package com.keviny.egg.controller;

import com.keviny.egg.interpreter.EggScanner;
import com.keviny.egg.interpreter.Token;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

// This class is a representation of the user's script (Drawing)
public class Drawing {
  private File script;
  private List<Token> tokens;

  // Executes the script and draws the image
  public void draw() {
    if (tokens == null || script == null) {
      System.out.println("No script to draw!");
    }
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
    setTokens();
  }

  private void setTokens() {
    try (FileInputStream fis = new FileInputStream(script)) {
      long size = script.length();
      byte[] byteArray = new byte[(int) size];
      fis.read(byteArray);

      EggScanner scanner = new EggScanner(new String(byteArray));

      tokens = scanner.scanTokens();
    } catch (Exception e) {
      System.err.println("Could not read file due to: " + e.getMessage());
    }
  }
}
