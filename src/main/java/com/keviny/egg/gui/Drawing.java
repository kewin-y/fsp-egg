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
import java.util.HashMap;
import java.util.List;

// This class is a representation of the user's script (Drawing)
public class Drawing {
  private File script;

  private List<Token> tokens;
  private List<EggError> scanningErrors;

  private Raylib.Color penColor;
  private Raylib.Vector2 penPosition;
  private Raylib.Vector2 penDirection;

  private static final HashMap<TokenType, Raylib.Color> COLORS;

  static {
    COLORS = new HashMap<>();
    COLORS.put(TokenType.RED, Jaylib.RED);
    COLORS.put(TokenType.ORANGE, Jaylib.ORANGE);
    COLORS.put(TokenType.YELLOW, Jaylib.YELLOW);
    COLORS.put(TokenType.GREEN, Jaylib.GREEN);
    COLORS.put(TokenType.BLUE, Jaylib.BLUE);
    COLORS.put(TokenType.PURPLE, Jaylib.PURPLE);
  }

  // Executes the script and draws the image
  public void draw() {
    if (tokens == null || script == null) {
      // TODO: Implement error
      System.out.println("No script to draw!");
      return;
    }
    if (scanningErrors.size() > 0) {
      System.out.println("Scanning error!");
      return;
    }

    int index = 0;

    resetPen();

    while (tokens.get(index).getType() != TokenType.EOF) {
      switch (tokens.get(index).getType()) {
        case PEN_COLOR:
          index++;

          Raylib.Color newColor = COLORS.get(tokens.get(index).getType());
          if (newColor == null) {
            System.out.println("Runtime Error!");
            return;
          }
          penColor = newColor;
          index++;
          break;
        case PEN_MOVE:
          index++;

          if (tokens.get(index).getType() != TokenType.NUMBER) {
            System.out.println("Runtime Error!");
            return;
          }

          // The magnitude of movement is the argument (next token)
          float mag = (float) tokens.get(index).getLiteral();
          // Displace ment is calculated using direction * magnitude
          Raylib.Vector2 displ = Jaylib.Vector2Scale(penDirection, mag);

          // Calculate new position and draw a line from old to new position
          Raylib.Vector2 newPos = Jaylib.Vector2Add(penPosition, displ);
          Jaylib.DrawLineEx(penPosition, newPos, 4, penColor);
          penPosition = newPos;

          index++;
          break;
        case PEN_ROTATE:
          index++;
          if (tokens.get(index).getType() != TokenType.NUMBER) {
            System.out.println("Runtime Error!");
            return;
          }

          float angle = (float) Math.toRadians((float) tokens.get(index).getLiteral());
        
          Raylib.Vector2 newDirection = Jaylib.Vector2Rotate(penDirection, angle);
          penDirection = newDirection;

          index++;
          break;
        case PEN_UP:
          penColor.a((byte) 0);
          index++;
          break;
        case PEN_DOWN:
          penColor.a((byte) 255);
          index++;
          break;
        default:
          break;
      }
    }
  }

  // Method that dispays an error
  public void error(int line, String message) {}

  private void report(int line, String where, String message) {}

  private void resetPen() {
    penColor = Jaylib.BLACK;
    penDirection = Jaylib.Vector2Normalize(new Jaylib.Vector2(0, -1));
    penPosition = EggStage.stageToScreenPos(new Jaylib.Vector2(0, 0));
  }

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

  // Scans the tokens of the file
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

    for (Token t : tokens) {
      System.out.println(t);
    }
  }
}
