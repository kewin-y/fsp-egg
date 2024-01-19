package com.keviny.egg.gui;

import com.keviny.egg.controller.EggController;
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

  // Drawing -> the thing that the code makes
  private Drawing drawing;

  private final EggController controller;

  public EggStage(EggController controller) {
    this.controller = controller;
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

    EggButton loadNewButton = new EggButton("assets/sprites/load_new.png", 581, 40);
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
    scriptIndicatorXPos = getCoordinateToCenter(scriptIndicator);
  }

  // Determines the proper x coordinate to center some text
  private int getCoordinateToCenter(String text) {
    return SCREEN_WIDTH / 2 - Jaylib.MeasureText(text, FONT_SIZE) / 2;
  }

  // The constructor which runs the main game loop
  // All the displaying of graphics happens here
  public void displayGui() {
    Raylib.Vector2 mousePosition = new Jaylib.Vector2();

    /*
     * Initialization
     */
    Jaylib.SetConfigFlags(Jaylib.FLAG_WINDOW_UNDECORATED);
    Jaylib.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Egg - Learn to Code");
    boolean exitWindow = false;
    Jaylib.SetTargetFPS(60);

    /*
     * Game Objects
     */
    initStageComponents();
    scriptIndicatorXPos = getCoordinateToCenter(scriptIndicator);
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

      // Window decoration
      exitWindow =
          Jaylib.GuiWindowBox(new Jaylib.Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT), "#198# Egg");

      // Draw buttons for the stage
      for (EggButton b : buttons) {
        b.draw();
      }

      // Display the user's drawing:
      drawing.draw();

      Jaylib.DrawText(scriptIndicator, scriptIndicatorXPos, 60, FONT_SIZE, Jaylib.GRAY);

      /*
       * End Drawing
       */
      Jaylib.EndDrawing();
    }

    /*
     * De-Initialization
     */
    Jaylib.CloseWindow(); // Close window and OpenGL context -> cleans resources as well
  }

  public static Raylib.Vector2 stageToScreenPos(Raylib.Vector2 pos) {
    return new Jaylib.Vector2(pos.x() + SCREEN_WIDTH / 2, pos.y() + SCREEN_HEIGHT / 2);
  }

  public void setButtons(ArrayList<EggButton> buttons) {
    this.buttons = buttons;
  }

  public String getScriptIndicator() {
    return scriptIndicator;
  }

  public void setScriptIndicator(String scriptIndicator) {
    this.scriptIndicator = scriptIndicator;
  }

  public int getScriptIndicatorXPos() {
    return scriptIndicatorXPos;
  }

  public void setScriptIndicatorXPos(int scriptIndicatorXPos) {
    this.scriptIndicatorXPos = scriptIndicatorXPos;
  }

  public Drawing getDrawing() {
    return drawing;
  }

  public void setDrawing(Drawing drawing) {
    this.drawing = drawing;
  }
}
