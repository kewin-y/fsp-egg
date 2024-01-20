package com.keviny.egg.gui;

// An error for when there is no script selected
import com.keviny.egg.interpreter.EggError;
import com.keviny.egg.interpreter.EggScanner;
import com.keviny.egg.interpreter.NoScriptError;
import com.keviny.egg.interpreter.Token;
import com.keviny.egg.interpreter.TokenType;
import com.raylib.Jaylib;
import com.raylib.Raylib;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// This class is a representation of the user's script (Drawing)
public class Drawing extends EggComponent {
  private static final Raylib.Vector2 DEFAULT_DIRECTION =
      Jaylib.Vector2Normalize(new Jaylib.Vector2(0, -1));
  private static final Raylib.Vector2 DEFAULT_POSITION =
      EggStage.stageToScreenPos(new Jaylib.Vector2(0, 0));
  private File script;

  private List<Token> tokens;
  private List<EggError> scanningErrors;

  private Raylib.Color penColor;
  private Raylib.Vector2 penPosition;
  private Raylib.Vector2 penDirection;

  // Since the drawing should tell the stage to stop
  // -> This is for error handling
  private final EggStage stage;

  private static final HashMap<TokenType, Raylib.Color> COLORS;

  static {
    COLORS = new HashMap<>();
    COLORS.put(TokenType.RED, Jaylib.RED);
    COLORS.put(TokenType.ORANGE, Jaylib.ORANGE);
    COLORS.put(TokenType.YELLOW, Jaylib.YELLOW);
    COLORS.put(TokenType.GREEN, Jaylib.GREEN);
    COLORS.put(TokenType.BLUE, Jaylib.BLUE);
    COLORS.put(TokenType.PURPLE, Jaylib.PURPLE);
    COLORS.put(TokenType.BLACK, Jaylib.BLACK);
  }

  // Constructor sets the stage field
  public Drawing(EggStage stage) {
    this.stage = stage;
    setShouldDraw(false);
  }

  // Executes the script and draws the image
  @Override
  public void draw() {
    if (!isShouldDraw()) return;

    if (tokens == null || script == null) {
      displayError(Arrays.asList(new NoScriptError()), "Error");
      return;
    }
    if (scanningErrors.size() > 0) {
      displayError(scanningErrors, "Scanning errors present in the script!");
      return;
    }

    executeTokens();
  }

  // This method loops through the tokens and creates a drawing accordingly
  private void executeTokens() {
    int index = 0;

    resetPen();
    while (tokens.get(index).getType() != TokenType.EOF) {
      switch (tokens.get(index).getType()) {
          // Color
        case PEN_COLOR:
          index++;

          Raylib.Color newColor = COLORS.get(tokens.get(index).getType());
          // Give an error if the next token is not a color
          if (newColor == null) {
            displayExecutionError(index);
            return;
          }

          // Change color and increment
          // Pattern repeats for other 2-part commands like move, rotate, etc.
          penColor = newColor;
          index++;
          break;
          // Move
        case PEN_MOVE:
          index++;

          if (tokens.get(index).getType() != TokenType.NUMBER) {
            displayExecutionError(index);
            return;
          }

          // The magnitude of movement is the argument (next token)
          float mag = (float) tokens.get(index).getLiteral();

          // Displacement is calculated using direction * magnitude
          Raylib.Vector2 displacement = Jaylib.Vector2Scale(penDirection, mag);

          // Calculate new position and draw a line from old to new position
          Raylib.Vector2 newPos = Jaylib.Vector2Add(penPosition, displacement);
          Jaylib.DrawLineEx(penPosition, newPos, 4, penColor);
          penPosition = newPos;

          index++;
          break;
          // Rotate
        case PEN_ROTATE:
          index++;
          if (tokens.get(index).getType() != TokenType.NUMBER) {
            displayExecutionError(index);
            return;
          }

          float angle = (float) Math.toRadians((float) tokens.get(index).getLiteral());

          Raylib.Vector2 newDirection = Jaylib.Vector2Rotate(penDirection, angle);
          penDirection = newDirection;

          index++;
          break;
        case PEN_UP:
          // Set alpha to zero -> makes invisible
          penColor.a((byte) 0);
          index++;
          break;
        case PEN_DOWN:
          // Set alpha to 255 -> makes opaque again
          penColor.a((byte) 255);
          index++;
          break;
        default:
          break;
      }
    }
  }

  // Methods to display errors:

  /**
   * Shows a panel with every error
   *
   * @param errors The list of errors to display
   * @param msg The title of the panel
   */
  private void displayError(List<EggError> errors, String msg) {
    var ep = new ErrorPanel(errors, msg);
    ep.show();
    setShouldDraw(false);
  }

  /**
   * Displays a runtime error for a token
   *
   * @param index The index of the erroneous token in the tokens field
   */
  private void displayExecutionError(int index) {
    Token token;

    // Since we don't want to report the end of a file as an error
    if (tokens.get(index).getType() == TokenType.EOF) {
      token = tokens.get(index - 1);
    } else {
      token = tokens.get(index);
    }

    EggError rerror = new EggError(token.getLine(), "Misplaced token", token.getLexeme());
    displayError(Arrays.asList(new EggError[] {rerror}), "Execution Error(s):");
  }

  // Resets the pen to it's default settings
  private void resetPen() {
    penColor = Jaylib.BLACK;
    penColor.a((byte) 255);
    penDirection = DEFAULT_DIRECTION;
    penPosition = DEFAULT_POSITION;
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
      return;
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
