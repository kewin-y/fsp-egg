package com.keviny.egg.interpreter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EggInterpreter {
  static boolean hadError = false;

  // Runs a file
  public static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    // 65 signifies that the input data was incorrect in some way
    // if (hadError) System.exit(65);
  }

  // Runs whatever source code is given.
  private static void run(String source) {
    EggScanner scanner = new EggScanner(source);
    List<Token> tokens = scanner.scanTokens();

    for (Token token : tokens) {
      System.out.println(token);
    }
  }

  // Method that sends an error message
  static void error(int line, String message) {
    report(line, "", message);
  }

  // Helper method for the error method
  private static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }
}
