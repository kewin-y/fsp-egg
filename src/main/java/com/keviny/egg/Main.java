// Was supposed to be called EGPI (Easy Graphics Programming Interface) but shortened to egg
package com.keviny.egg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
  static boolean hadError = false;

  public static void main(String[] args) throws IOException {
    if (args.length > 1) { // More than 1 arguments given: exit the program
      System.out.println("Usage: egg [script]");
      System.exit(64); // 64 indicates improper usage
    } else if (args.length == 1) { // 1 argument: run the program
      runFile(args[0]);
    } else { // No arguments: "Shell" mode
      runPrompt();
    }
  }

  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    if (hadError) System.exit(65); // Input data is incorrect
  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);
    for (; ; ) {
      System.out.print("> ");
      String line = reader.readLine();

      // readLine() returns null when EOF (end-of-file) signal is recieved
      // Usually signalled when Ctrl-D is pressed
      if (line == null) break;
      run(line);
      hadError = false;
    }
  }

  private static void run(String source) {
    EggScanner scanner = new EggScanner();
    List<Token> tokens = scanner.scanTokens();

    for (Token token : tokens) {
      System.out.println(token);
    }
  }

  static void error(int line, String message) {
    report(line, "", message);
  }

  private static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }
}
