package com.keviny.egg.gui;

import com.keviny.egg.controller.EggStageController;
import com.raylib.Jaylib;
import com.raylib.Raylib;
import java.io.File;
import java.util.ArrayList;

// This class the main GUI (View Component)
public class EggStage {
  // Some constants for the GUI components
  private static final int SCREEN_WIDTH = 1280;
  private static final int SCREEN_HEIGHT = 720;
  private static final int BUTTON_Y_BOT = 640;
  private static final int FONT_SIZE = 16;

  private ArrayList<EggButton> buttons = new ArrayList<>();

  // Script -> the code
  private String scriptIndicator = "[No Script Selected]";
  private int scriptIndicatorXPos;

  // Drawing -> the "thing" (drawing) that the code makes
  private Drawing drawing;

  private final EggStageController controller;

  // Constructor which initializes the controller
  // Controller is a field of the stage (for clarity)
  public EggStage() {
    controller = new EggStageController(this);
    displayGui();
  }

  // Creates the components of the stage
  private void initStageComponents() {
    // The Drawing:
    drawing = new Drawing(this);
    // Create the buttons and attach their functions
    EggButton pausePlayButton = new EggButton("assets/sprites/play.png", 598, BUTTON_Y_BOT);
    pausePlayButton.attachFunction(controller.handlePlay);

    EggButton stopButton = new EggButton("assets/sprites/stop.png", 650, BUTTON_Y_BOT);
    stopButton.attachFunction(controller.handleStop);

    EggButton loadNewButton = new EggButton("assets/sprites/load_new.png", 581, 20);
    loadNewButton.attachFunction(controller.handleLoadNew);

    buttons.add(pausePlayButton);
    buttons.add(stopButton);
    buttons.add(loadNewButton);
  }

  // Change the drawing's script and update the indicators
  public void updateScript(File script) {
    if (script == null) {
      return;
    }

    drawing.setScript(script);
    scriptIndicator = String.format("[%s]", drawing.getScript().getName());
    scriptIndicatorXPos = centerTextX(scriptIndicator);
  }

  // Determines the proper x coordinate to center some text

  // The constructor which runs the main game loop
  // All the displaying of graphics happens here
  public void displayGui() {
    Raylib.Vector2 mousePosition = new Jaylib.Vector2();

    /*
     * Initialization
     */
    Jaylib.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Egg - Learn to Code");
    boolean exitWindow = false;
    Jaylib.SetTargetFPS(60);

    /*
     * Game Objects
     */
    initStageComponents();
    scriptIndicatorXPos = centerTextX(scriptIndicator);
    // Selected]
    // Main game loop
    while (!exitWindow && !Jaylib.WindowShouldClose()) // Detect window close button or ESC key
    {
      /*
       * === Update ===
       */
      mousePosition = Jaylib.GetMousePosition();

      // Poll buttons for the stage
      for (EggButton b : buttons) {
        b.poll(mousePosition);
      }

      /*
       * === Draw ===
       */
      Jaylib.BeginDrawing();

      Jaylib.ClearBackground(Jaylib.RAYWHITE);

      // Display the user's drawing:
      drawing.draw();

      // Draw buttons for the stage
      for (EggButton b : buttons) {
        b.draw();
      }

      Jaylib.DrawText(scriptIndicator, scriptIndicatorXPos, 40, FONT_SIZE, Jaylib.GRAY);

      // Draw the info panels

      /*
       * End Drawing
       */
      Jaylib.EndDrawing();
    }

    /*
     * De-Initialization
     */
    Jaylib.CloseWindow(); // Close window and OpenGL context
  }

  /**
   * @param pos The {@code}Raylib.Vector2 in stage pos (The Stage pos treats the center of the
   *     screen as (0, 0))
   * @return The converted coordinate from stage pos to screen pos (The screen pos treats the top
        left corner of the screen as (0, 0))
   */
  public static Raylib.Vector2 stageToScreenPos(Raylib.Vector2 pos) {
    return new Jaylib.Vector2(pos.x() + SCREEN_WIDTH / 2, pos.y() + SCREEN_HEIGHT / 2);
  }

  /**
   * @param text The text to center
   * @return The x-coordinate of the centered text
   */
  public static int centerTextX(String text) {
    return centerX(Jaylib.MeasureText(text, FONT_SIZE));
  }

  /**
   * @param w The width of the component
   * @return The x-coordinate of the centered component (Along X-axis)
   */
  public static int centerX(int w) {
    return SCREEN_WIDTH / 2 - w / 2;
  }

  /**
   * @param h The height of the component
   * @return The y-coordinate of the centered component (Along Y-axis)
   */
  public static int centerY(int h) {
    return SCREEN_HEIGHT / 2 - h / 2;
  }

  public Drawing getDrawing() {
    return drawing;
  }

  public void setDrawing(Drawing drawing) {
    this.drawing = drawing;
  }
}
